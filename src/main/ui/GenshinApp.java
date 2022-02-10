package ui;

import model.Character;
import model.CharacterList;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Genshin Application
public class GenshinApp {
    private CharacterList owned;
    private Scanner input;
    List<CharacterList> lists = new ArrayList<>();
    private boolean keepGoing = true;

    // EFFECTS: runs the teller application
    public GenshinApp() {
        runGenshin();
    }

    private void runGenshin() {

        String request = "";

        init();

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
        System.out.println("It was fun chatting with you. See you again!");
        keepGoing = false;
    }

    // MODIFIES: this
    // EFFECTS: initializes starting list and input
    private void init() {
        owned = new CharacterList("Owned Characters");
        lists.add(owned);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("Hi there! My name is Paimon! Welcome to your Genshin Tracker");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n");
        System.out.println("What would you like me to help you with?");
        System.out.println("\nSelect from 1-5");
        System.out.println("\t1. add character");
        System.out.println("\t2. remove character");
        System.out.println("\t3. create new list");
        System.out.println("\t4. view lists");
        System.out.println("\t5. quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user's request in main menu
    private void processRequest(String request) {
        if (request.equals("1")) {
            addCharacter();
        } else if (request.equals("2")) {
            removeCharacter();
        } else if (request.equals("3")) {
            createList();
        } else if (request.equals("4")) {
            viewList();
        } else if (request.equals("5")) {
            quit();
        } else {
            System.out.println("Huh? Paimon doesn’t understand. Are you sure you entered a number form 1-5?");
        }
    }

    // EFFECTS: gives user option of quitting or continuing
    private void isContinue() {
        System.out.println("Does Paimon need to help you with anything else? (y/n)");
        String answer = input.next();
        if (answer.equals("n")) {
            quit();
        } else if (! answer.equals("y")) {
            System.out.println("Hmm that doesn’t make sense to Paimon, are you sure you entered correctly?");
        }
    }

    // EFFECTS: creates a list and assigns it a name
    private void createList() {
        System.out.println("What would you like your list name to be?");
        String listName = input.next();
        lists.add(new CharacterList(listName));
        System.out.println("List " + listName + " created! \n");
        isContinue();
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
                + " wielding level " + createdCharacter.getLevel() + " " + createdCharacter.getElement()
                + " character has been added to your " + selected.getName() + " list!");
        isContinue();
    }

    // MODIFIES: selected
    // EFFECTS: creates a character and assigns their traits
    private Character createCharacter() {
        int level = 0;
        System.out.println("What’s your character's name?");
        String name = input.next();
        System.out.println("What element does " + name + " use");
        String element = input.next();
        System.out.println(element + "! That's so cool! Paimon wishes she had a cool element \n"
                + "... Anyways, what weapon does " + name + " use?");
        String weapon = input.next();
        System.out.println("Eek! They're a " + weapon + " user? Paimon doesn't like weapons \n"
                + "Last but not least, what is " + name + "'s level?");
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
        return new Character(name, element, weapon, level);
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
        isContinue();
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
        isContinue();
    }

    // EFFECTS: asks user to choose a list
    private CharacterList selectList() {
        int selected = 0;  // force entry into loop
        if (lists.size() == 1) {
            System.out.println("Looks like you only have one list!");
            return lists.get(0);
        } else {
            while (!(0 < selected && selected <= lists.size())) {
                System.out.println("Enter 1 for the first list, 2 for the second, all the way to a bazillion for the "
                        + "bazillionth list! Paimon doesn’t think she has seen a bazillion of anything before...");
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println((i + 1) + ". " + lists.get(i).getName());
                }
                try {
                    selected = Integer.parseInt(input.next());
                    if (!(0 < selected && selected <= (lists.size()))) {
                        System.out.println("That makes no sense! The number must be between 1 to " + lists.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Hmm that doesn’t make sense to Paimon, are you sure you entered a number?");
                }
            }
        }
        selected--;
        return lists.get(selected);
    }

    // EFFECTS: returns list of characters in given list
    private void printList(CharacterList charList) {
        for (int i = 0; i < charList.getSize(); i++) {
            System.out.println((i + 1) + ". " + charList.getCharacter(i).getName());
        }
    }

}
