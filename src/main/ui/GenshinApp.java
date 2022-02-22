package ui;

import model.*;

import model.Character;
import persistence.JsonReader;
import persistence.JsonWriter;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Genshin Application
public class GenshinApp {
    private static final String JSON_STORE = "./data/lists.json";
    private CharacterList owned;
    private Scanner input;
    private boolean keepGoing = true;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    Profile profile = new Profile();


    // EFFECTS: triggers the run genshin application method
    public GenshinApp() {
        runGenshin();

    }

    // EFFECTS: runs the genshin application
    private void runGenshin() {
        String request;
        init();
        System.out.println("Welcome back! Would you like Paimon to restore list information "
                + "from your previous session? (y/n)");
        String restore = input.next();
        if (restore.equals("y")) {
            loadProfile();
        } else if (restore.equals("n")) {
            System.out.println("Great! Paimon will start a new profile for you!");
        }

        while (keepGoing) {
            displayMenu();
            request = input.next();
            request = request.toLowerCase();
            processRequest(request);
        }
    }

    //MODIFIES: keepGoing
    // EFFECTS: stops application
    private void quit() {
        System.out.println("Phew! Thats enough list making for today! Would you like Paimon to save your work? (y/n)\n");
        String reply = input.next();
        if (reply.equals("y")) {
            saveProile();
        }
        System.out.println("It was fun chatting with you. See you again!");
        keepGoing = false;
    }

    // MODIFIES: this
    // EFFECTS: initializes starting list, input, and saving abilities
    private void init() {
        owned = new CharacterList("Owned Characters");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        profile.addList(owned);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Hi there! My name is Paimon! Welcome to your Genshin Tracker");

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n");
        System.out.println("What would you like me to help you with?");
        System.out.println("\nSelect from 1-6");
        System.out.println("\t1. add character");
        System.out.println("\t2. remove character");
        System.out.println("\t3. create new list");
        System.out.println("\t4. view lists");
        System.out.println("\t5  save lists to file");
        System.out.println("\t6. quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user's request in main menu
    private void processRequest(String request) {
        if (request.equals("1")) {
            addCharacter();
            isContinue();
        } else if (request.equals("2")) {
            removeCharacter();
            isContinue();
        } else if (request.equals("3")) {
            createList();
            isContinue();
        } else if (request.equals("4")) {
            viewList();
            isContinue();
        } else if (request.equals("5")) {
            saveProile();
            isContinue();
        } else if (request.equals("6")) {
            quit();
        } else {
            System.out.println("Huh? Paimon doesn’t understand. Are you sure you entered a number form 1-6?");
        }
    }

    // EFFECTS: gives user option of quitting or continuing
    private void isContinue() {
        System.out.println("Does Paimon need to help you with anything else? (y/n)");
        String answer = input.next();
        if (answer.equals("n")) {
            quit();
        } else if (!answer.equals("y")) {
            System.out.println("Hmm that doesn’t make sense to Paimon, are you sure you entered correctly?");
        }
    }

    // EFFECTS: creates a list and assigns it a name
    private void createList() {
        System.out.println("What would you like your list name to be?");
        String listName = input.next();
        profile.addList(new CharacterList(listName));
        System.out.println("List " + listName + " created! \n");

    }

    // MODIFIES: selected
    // EFFECTS: creates a list and assigns it a name
    private void addCharacter() {
        System.out.println("Ooh we’re adding a new character? That’s exciting!");
        System.out.println("Which list would you like to add to?");
        CharacterList selected = selectList();
        System.out.println("You are now adding to " + selected.getName());
        Character createdCharacter = createCharacter();
        selected.addCharacterToList(createdCharacter);
        System.out.println("Got it!");
        System.out.println(createdCharacter.getName() + " the " + createdCharacter.getWeapon()
                + " wielding level " + createdCharacter.getLevel() + " " + createdCharacter.getVision()
                + " character has been added to your " + selected.getName() + " list!");
    }

    // MODIFIES: selected
    // EFFECTS: creates a character and assigns their traits
    private Character createCharacter() {
        System.out.println("What’s your character's name?");
        String name = input.next();
        Vision vision = readVision(name);
        Weapon weapon = readWeapon(name);
        int level = readLevel(name);
        return new Character(name, vision, weapon, level);
    }

    // EFFECTS: prompts user to select a vision and returns it
    private Vision readVision(String name) {
        System.out.println("What vision does " + name + " have");

        int menuLabel = 1;
        for (Vision v : Vision.values()) {
            System.out.println(menuLabel + ": " + v);
            menuLabel++;
        }
        int menuSelection = input.nextInt();
        System.out.println(Vision.values()[menuSelection - 1] + "! "
                + "That's so cool! Paimon wishes she had a cool element \n");

        return Vision.values()[menuSelection - 1];
    }

    // EFFECTS: prompts user to select a weapon and returns it
    private Weapon readWeapon(String name) {
        System.out.println("... Anyways, what weapon does " + name + " use?");

        int menuLabel = 1;
        for (Weapon w : Weapon.values()) {
            System.out.println(menuLabel + ": " + w);
            menuLabel++;
        }

        int menuSelection = input.nextInt();
        System.out.println("Eek! They're a " + Weapon.values()[menuSelection - 1] + " user? "
                + "Paimon doesn't like weapons \n");
        return Weapon.values()[menuSelection - 1];
    }

    // EFFECTS: prompts user to select a vision and returns it
    private int readLevel(String name) {
        int level = 0;
        System.out.println("Last but not least, what is " + name + "'s level?");
        while ((level > 90) || level <= 0) {
            try {
                level = Integer.parseInt(input.next());
                if (level > 90) {
                    System.out.println("You can't trick Paimon! A character can't be above level 90. Try again");
                } else if (level <= 0) {
                    System.out.println("You can't trick Paimon! A character can't be below level 1. Try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Hmm that doesn’t make sense to Paimon, are you sure you entered correctly?");
            }
        }
        return level;
    }

    // MODIFIES: selected
    // EFFECTS: removes character from chosen list
    private void removeCharacter() {
        System.out.println("You want to kick someone out? From which list?");
        CharacterList selected = selectList();
        if (selected.empty()) {
            System.out.println("Uh oh, looks like the list you selected has no characters to remove \n");
        } else {
            System.out.println("You are removing from list " + selected.getName());
            System.out.println("Who are you going to remove?");
            String name = input.next();
            if (selected.inList(name)) {
                selected.removeCharacterFromList(selected.nameToCharacter(name));
                System.out.println("Goodbye " + name + "! I have removed them from your "
                        + selected.getName() + " list");
            } else {
                System.out.println("Hmmm, from Paimon's records " + name + " doesn’t seem to be on this list");
            }
        }
    }

    // EFFECTS: allows user to view characters in selected list
    private void viewList() {
        CharacterList selected = selectList();
        System.out.println("Here's a list of all the characters in your " + selected.getName() + " list!");
        if (selected.getSize() == 0) {
            System.out.println("You don't have any characters in this list!");
        } else {
            printList(selected);
        }
    }

    // EFFECTS: asks user to choose a list
    private CharacterList selectList() {
        int selected = 0;  // force entry into loop
        if (profile.getListSize() == 1) {
            System.out.println("Looks like you only have one list!");
            return profile.getList(0);
        } else {
            while (!(0 < selected && selected <= profile.getListSize())) {
                System.out.println("Enter 1 for the first list, 2 for the second, all the way to a bazillion for the "
                        + "bazillionth list! Paimon doesn’t think she has seen a bazillion of anything before...");
                for (int i = 0; i < profile.getListSize(); i++) {
                    System.out.println((i + 1) + ". " + profile.getList(i).getName());
                }
                try {
                    selected = Integer.parseInt(input.next());
                    if (!(0 < selected && selected <= (profile.getListSize()))) {
                        System.out.println("The number must be between 1 and " + profile.getListSize());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Hmm that doesn’t make sense to Paimon, are you sure you entered a number?");
                }
            }
        }
        selected--;
        return profile.getList(selected);
    }

    // EFFECTS: returns list of characters in given list
    private void printList(CharacterList charList) {
        for (int i = 0; i < charList.getSize(); i++) {
            System.out.println((i + 1) + ". " + charList.getCharacter(i).getName());
        }
    }

    //MODIFIES
    // EFFECTS: saves profile to file
    private void saveProile() {
        try {
            jsonWriter.open();
            jsonWriter.write(profile);
            jsonWriter.close();
            System.out.println("Good idea! Paimon has saved your list information to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Hmrph, Paimon is stumped and can't save your lists to " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads profile from file
    private void loadProfile() {
        try {
            profile = jsonReader.read();
            System.out.println("Woohoo, your previous work has been loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Arg! Paimon wasn't able to read from file: " + JSON_STORE);
        }
    }

}
