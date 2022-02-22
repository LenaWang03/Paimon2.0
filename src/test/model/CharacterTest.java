package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterTest {
    private Character Eula;
    private Character Albedo;


    @BeforeEach
    void runBefore() {
        Eula = new Character("Eula", Vision.CRYO, Weapon.CLAYMORE, 80);
        Albedo = new Character("Albedo", Vision.GEO, Weapon.SWORD, 80);
    }

    @Test
    void constructorTest() {
        assertEquals("Eula", Eula.getName());
        assertEquals(Vision.CRYO, Eula.getVision());
        assertEquals(Weapon.CLAYMORE, Eula.getWeapon());
        assertEquals(80, Eula.getLevel());
        assertEquals("Albedo", Albedo.getName());
        assertEquals(Vision.GEO, Albedo.getVision());
        assertEquals(Weapon.SWORD, Albedo.getWeapon());
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