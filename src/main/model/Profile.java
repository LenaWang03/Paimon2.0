package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Profile implements Writable {

    private List<CharacterList> lists;
    private String name;

    public Profile(String name) {
        this.name = name;
        lists = new ArrayList<>();
    }

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

    public void addList(CharacterList cl) {
        lists.add(cl);
    }

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
