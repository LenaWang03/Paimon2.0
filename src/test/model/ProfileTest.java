package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile testProfile;
    private CharacterList testHealer;
    private CharacterList testDPS;

    @BeforeEach
    void runBefore() {
        testProfile = new Profile("Lena");
        testHealer = new CharacterList("Healer");
        testDPS = new CharacterList("DPS");
    }

    @Test
    void constructorTest() {
        assertEquals("Lena", testProfile.getName());
        assertEquals(0, testProfile.getSize());
    }

    @Test
    void testAddList() {
        testProfile.addList(testHealer);
        assertEquals(testHealer, testProfile.getList(0));
        assertEquals(1, testProfile.getSize());
    }

    @Test
    void testToJson() {
        testProfile.toJson();
        assertEquals(2, testProfile.toJson().length());
        assertEquals("Lena", testProfile.toJson().getString("name"));
    }

    @Test
    void testCharacterToJson() {
        testProfile.addList(testHealer);
        testProfile.toJson();
        assertEquals(1, testProfile.toJson().getJSONArray("lists").length());
        testProfile.addList(testDPS);
        testProfile.toJson();
        assertEquals(2, testProfile.toJson().getJSONArray("lists").length());
    }
}
