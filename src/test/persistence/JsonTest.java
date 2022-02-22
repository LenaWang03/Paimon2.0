package persistence;

import model.Character;
import model.Vision;
import model.Weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCharacter(String n, Vision v, Weapon w, int l, Character c) {
        assertEquals(n, c.getName());
        assertEquals(v, c.getVision());
        assertEquals(w, c.getWeapon());
        assertEquals(l, c.getLevel());
    }
}