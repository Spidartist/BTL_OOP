package objects;

import org.json.simple.JSONObject;

import javafx.collections.ObservableList;
import objects.dynasty.Dynasty;

public interface ParseJSON {
    public Object parseDataObject(JSONObject data);
    
}
