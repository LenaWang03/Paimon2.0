package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterTest {
    private Character Eula;
    private Character Albedo;


    @BeforeEach
    void runBefore() {
        Eula = new Character("Eula", "Cryo", "Claymore", 80);
        Albedo = new Character("Albedo", "Geo", "Sword", 80);
    }

    @Test
    void constructorTest() {
        assertEquals("Eula", Eula.getName());
        assertEquals("Cryo", Eula.getElement());
        assertEquals("Claymore", Eula.getWeapon());
        assertEquals(80, Eula.getLevel());
        assertEquals("Albedo", Albedo.getName());
        assertEquals("Geo", Albedo.getElement());
        assertEquals("Sword", Albedo.getWeapon());
        assertEquals(80, Albedo.getLevel());
    }

    @Test
    void testIncreaseLevel() {
        Eula.increaseLevel(12);
        assertEquals(90, Eula.getLevel());
        Albedo.increaseLevel(4);
        assertEquals(84, Albedo.getLevel());
    }
}