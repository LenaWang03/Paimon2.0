package model;

// represents a genshin character that stores their stats and traits
public class Character {

    //fields
    String name;      // character's name
    String element;   // character's element
    String weapon;    // character's weapon type
    int level;        // character's current level

    /*
    * REQUIRES: element be one of "pyro", "cryo", "electro", "hydro", "anemo", "dendro" , "geo"
    *           weapon be one of "sword", "spear", "claymore", "bow"
    *           level > 0
    * EFFECTS: creates character set with traits that user inputs
    */
    public Character(String name, String element, String weapon, int level) {
        this.name = name;
        this.element = element;
        this.weapon = weapon;
        this.level = level;
    }

    /*
     * REQUIRES: amount > 0
     * MODIFIES: this
     * EFFECTS: increases character level unless character has already reached level 90
     */
    public void increaseLevel(int amount) {
        if (level + amount <= 90) {
            level += amount;
        } else {
            level = 90;
        }
    }

    // get functions for testing

    public String getName() {
        return name;
    }

    public String getElement() {
        return element;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getLevel() {
        return level;
    }

}
