package model;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterListTest {
    private CharacterList testList;
    private Character Eula;
    private Character Albedo;

    @BeforeEach
    void runBefore() {
        testList = new CharacterList("Owned Characters");
        Eula = new Character("Eula", Vision.CRYO, Weapon.CLAYMORE, 80);
        Albedo = new Character("Albedo", Vision.GEO, Weapon.SWORD, 80);
    }

    @Test
    void constructorTest() {
        assertEquals("Owned Characters", testList.getName());
        List<Character> characters = testList.getCharacters();
        assertTrue(characters.isEmpty());
    }

    @Test
    void testAddCharacter() {
        testList.addCharacterToList(Eula);
        assertEquals(1, testList.getCharacters().size());
        assertEquals(Eula, testList.getCharacters().get(0));
    }

    @Test
    void testRemoveCharacter() {
        testList.addCharacterToList(Eula);
        testList.removeCharacterFromList(Eula);
        assertEquals(0, testList.getCharacters().size());
    }

    @Test
    void testInList() {
        testList.addCharacterToList(Albedo);
        testList.addCharacterToList(Eula);
        assertTrue(testList.inList("Eula"));
        assertTrue(testList.inList("Albedo"));
        assertFalse(testList.inList("Bennett"));
    }

    @Test
    void testNameToCharacter() {
        testList.addCharacterToList(Eula);
        assertEquals(Eula, testList.nameToCharacter("Eula"));
        assertEquals(null, testList.nameToCharacter("Albedo"));
    }

    @Test
    void testEmpty() {
        assertTrue(testList.empty());
        testList.addCharacterToList(Eula);
        assertFalse(testList.empty());
    }

    @Test
    void testgetSize() {
        assertEquals(0, testList.getSize());
    }

    @Test
    void testGetCharacter() {
        testList.addCharacterToList(Eula);
        testList.addCharacterToList(Albedo);
        assertEquals(Eula, testList.getCharacter(0));
        assertEquals(Albedo, testList.getCharacter(1));
    }

    @Test
    void testToJson() {
        testList.toJson();
        assertEquals(2, testList.toJson().length());
        assertEquals("Owned Characters", testList.toJson().getString("name"));
    }

    @Test
    void testCharacterToJson() {
        testList.addCharacterToList(Eula);
        testList.toJson();
        assertEquals(1, testList.toJson().getJSONArray("characters").length());
        testList.addCharacterToList(Albedo);
        testList.toJson();
        assertEquals(2, testList.toJson().getJSONArray("characters").length());
    }
}
