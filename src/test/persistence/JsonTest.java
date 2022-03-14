package persistence;

import model.Character;
import model.Vision;
import model.Weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;

// *some code from phase 2 based on JSONSerializationDemo application provided in course materials
public class JsonTest {
    protected void checkCharacter(String n, Vision v, Weapon w, int l, Character c) {
        assertEquals(n, c.getName());
        assertEquals(v, c.getVision());
        assertEquals(w, c.getWeapon());
        assertEquals(l, c.getLevel());
    }
}