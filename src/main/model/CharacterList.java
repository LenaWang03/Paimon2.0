package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a list of genshin characters
public class CharacterList implements Writable {


    // fields
    private String name; // name of the list
    private List<Character> characters; // list of characters

    // REQUIRES: name to not be equal to pre-existing list
    // EFFECTS: creates new character list with a name
    public CharacterList(String name) {
        this.name = name;
        characters = new ArrayList<>();
    }

    // REQUIRES: character to not already be in list
    // MODIFIES: this
    // EFFECTS: adds character to list
    public void addCharacterToList(Character character) {
        characters.add(character);
        EventLog.getInstance().logEvent(new Event(character.getName() + " added to list " + name));
    }

    // REQUIRES: character to be in list
    // MODIFIES: this
    // EFFECTS: removes character from list
    public void removeCharacterFromList(Character character) {
        characters.remove(character);
        EventLog.getInstance().logEvent(new Event(character.getName() + " removed from list " + name));
    }

     // EFFECTS: returns true if character is in the list, else false
    public boolean inList(String name) {
        for (int i = 0; i < characters.size(); i++) {
            if (name.equals(characters.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

     // REQUIRES: character to be in list
     // EFFECTS: returns character based on given name
    public Character nameToCharacter(String name) {
        int index = -1;
        for (int i = 0; i < characters.size(); i++) {
            if (name.equals(characters.get(i).getName())) {
                index = i;
            }
        }
        if (index == -1) {
            return null;
        } else {
            return characters.get(index);
        }
    }

    // EFFECTS: returns true if character list is empty, false otherwise
    public boolean empty() {
        return characters.isEmpty();
    }

    // EFFECTS: returns JSON object of character list name and characters
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("characters", charactersToJson());
        return json;
    }

    // EFFECTS: returns characters in this character list as a JSON array
    private JSONArray charactersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Character c : characters) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // getter methods for testing

    public String getName() {
        return name;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }

    public Character getCharacter(int index) {
        return this.characters.get(index);
    }

    public int getSize() {
        return this.characters.size();
    }

}
