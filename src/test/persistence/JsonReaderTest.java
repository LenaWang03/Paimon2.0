package persistence;

import model.Profile;
import model.Vision;
import model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// *some code from phase 2 based on JSONSerializationDemo application provided in course materials
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Profile testProfile = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json");
        try {
            Profile testProfile = reader.read();
            assertEquals(0, testProfile.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            Profile testProfile = reader.read();
            assertEquals("Lena", testProfile.getName());
            assertEquals(2, testProfile.getList(0).getSize());
            checkCharacter("Eula", Vision.CRYO, Weapon.CLAYMORE, 30,
                    testProfile.getList(0).getCharacter(0));
            checkCharacter("Albedo", Vision.GEO, Weapon.SWORD, 80,
                    testProfile.getList(0).getCharacter(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}