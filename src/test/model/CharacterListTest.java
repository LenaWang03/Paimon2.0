package model;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterListTest {
    private CharacterList testList;
    private Character Eula;

    @BeforeEach
    void runBefore() {
        testList = new CharacterList("Owned Characters");
        Eula = new Character("Eula", "Cryo", "Claymore", 80);
    }

    @Test
    void constructorTest() {
        assertEquals("Owned Characters", testList.getName());
        List<Character> characters = testList.getCharacters();
        assertTrue(characters.isEmpty());
    }

    @Test
    void testAddCharacter() {
        testList.addCharacter(Eula);
        assertEquals(1, testList.getCharacters().size());
        assertEquals(Eula, testList.getCharacters().get(0));
    }

    @Test
    void testRemoveCharacter() {
        testList.addCharacter(Eula);
        testList.removeCharacter(Eula);
        assertEquals(0, testList.getCharacters().size());
    }

}
