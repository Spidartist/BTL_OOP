package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

public class ReadJson {
    private ObservableList<King> kingList = FXCollections.observableArrayList();
    private ObservableList<Figure> figureList = FXCollections.observableArrayList();
    private ObservableList<Dynasty> dynastyList = FXCollections.observableArrayList();

    public ObservableList<King> getKingList() {
        JSONArray dataList = readData("src/data/king.json");
        for (int i = 0; i < dataList.size(); i++) {
            kingList.add(new King().parseDataObject((JSONObject) dataList.get(i)));
        }
        return kingList;
    }

    public ObservableList<Figure> getFigureList() {
        JSONArray dataList = readData("src/data/figureUpdate.json");
        for (int i = 0; i < dataList.size(); i++) {
            figureList.add(new Figure().parseDataObject((JSONObject) dataList.get(i)));
        }
        return figureList;
    }

    public ObservableList<Dynasty> getDinastyList() {
        JSONArray dataList = readData("src/data/dynastys.json");
        for (int i = 0; i < dataList.size(); i++) {
            dynastyList.add(new Dynasty().parseDataObject((JSONObject) dataList.get(i)));
        }
        return dynastyList;
    }

    @SuppressWarnings("unchecked")
    public JSONArray readData(String path) {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        JSONArray dataList = null;
        try (FileReader reader = new FileReader(path)) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);
            dataList = (JSONArray) obj;

            // System.out.println(employeeList);

            // Iterate over employee array
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dataList;
        // System.out.println(kingList.get(0).getMieuHieu());
    }

}
