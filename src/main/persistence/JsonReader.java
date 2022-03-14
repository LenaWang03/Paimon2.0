package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Character;
import org.json.*;

// *some code from phase 2 based on JSONSerializationDemo application provided in course materials
// Represents a reader that reads a user profile from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads profile from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Profile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProfile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses profile from JSON object and returns it
    private Profile parseProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Profile p = new Profile(name);
        addLists(p, jsonObject);
        return p;
    }

    // EFFECTS: parses character list from JSON object adds them to profile
    private void addLists(Profile p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lists");
        for (Object json : jsonArray) {
            JSONObject nextList = (JSONObject) json;
            addList(p, nextList);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses list from JSON object and adds it to profile
    private void addList(Profile p, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CharacterList cl = new CharacterList(name);
        p.addList(cl);
        addCharacters(cl, jsonObject);
    }

    // MODIFIES: cl
    // EFFECTS: parses characters from JSON object and adds them to profile
    private void addCharacters(CharacterList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("characters");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addCharacter(cl, nextCharacter);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses character from JSON object and adds it to profile
    private void addCharacter(CharacterList cl, JSONObject jsonObject) {
        Vision vision = Vision.valueOf(jsonObject.getString("vision"));
        Weapon weapon = Weapon.valueOf(jsonObject.getString("weapon"));
        int level = jsonObject.getInt("level");
        String name = jsonObject.getString("name");
        Character character = new Character(name, vision, weapon, level);
        cl.addCharacterToList(character);
    }
}
