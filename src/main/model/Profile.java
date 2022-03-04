package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a profile that is dedicated to the current user that stores all their info
public class Profile implements Writable {

    private List<CharacterList> lists;
    private String name;

    // EFFECTS: constructs a profile and assigns it a name
    public Profile(String name) {
        this.name = name;
        lists = new ArrayList<>();
    }

    // EFFECTS: returns the profile info as a json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lists", listsToJson());
        return json;
    }

    // EFFECTS: returns character lists in this profile as a JSON array
    private JSONArray listsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CharacterList cl : lists) {
            jsonArray.put(cl.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: adds a character list to the profile
    public void addList(CharacterList cl) {
        lists.add(cl);
    }

    // getter methods

    public int getSize() {
        return lists.size();
    }

    public String getName() {
        return name;
    }

    public CharacterList getList(int i) {
        return lists.get(i);
    }
}
