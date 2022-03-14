package persistence;

import model.CharacterList;
import model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// *some code from phase 2 based on JSONSerializationDemo application provided in course materials
public class JsonWriterTest {
    Profile testProfile;
    JsonWriter writer;

    @BeforeEach
    void runBefore() {
        testProfile = new Profile("Lena");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(testProfile);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            testProfile = reader.read();
            assertEquals("Lena", testProfile.getName());
            assertEquals(0, testProfile.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Profile p = new Profile("Lena");
            p.addList(new CharacterList("DPS"));
            p.addList(new CharacterList("Healer"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            p = reader.read();
            assertEquals("Lena", p.getName());
            assertEquals(2, p.getSize());
            assertEquals("DPS", p.getList(0).getName());
            assertEquals("Healer", p.getList(1).getName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
