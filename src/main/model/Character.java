package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a genshin character that stores their stats and traits
public class Character implements Writable {

    //fields
    String name;      // character's name
    Vision vision;    // character's vision
    Weapon weapon;    // character's weapon type
    int level;        // character's current level

    /*
    * REQUIRES: level > 0
    * EFFECTS: creates character set with traits that user inputs
    */
    public Character(String name, Vision vision, Weapon weapon, int level) {
        this.name = name;
        this.vision = vision;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("level", level);
        json.put("vision", vision);
        json.put("weapon", weapon);

        return json;
    }

    // get functions for testing

    public String getName() {
        return name;
    }

    public Vision getVision() {
        return vision;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getLevel() {
        return level;
    }

}
