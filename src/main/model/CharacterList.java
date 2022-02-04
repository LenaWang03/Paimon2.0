package model;

import java.util.ArrayList;
import java.util.List;
import ui.Main;

public class CharacterList {
    // represents a list of genshin characters

    // fields
    private String name; // name of the list
    private List<Character> characters;

    /*
     * REQUIRES: name to not be equal to pre-existing list
     * EFFECTS: creates new character list with
     */

    public CharacterList(String name) {
        this.name = name;
        characters = new ArrayList<>();
    }

    /*
     * REQUIRES: character to not already be in list
     * MODIFIES: this
     * EFFECTS: adds character to list
     */
    public void addCharacter(Character character) {
        characters.add(character);
    }

    /*
     * REQUIRES: character to be in list
     * MODIFIES: this
     * EFFECTS: removes character from list
     */
    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    // get functions for testing

    public String getName() {
        return name;
    }

    public List<Character> getCharacters() {
        return this.characters;
    }


}
