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
import objects.figure.King;

public class ReadJson {
    private ObservableList<King> kingList = FXCollections.observableArrayList();

    public ObservableList<King> getKingList() {
        readData("E:/OOP/BTL_OOP/king.json");
        return kingList;
    }

    @SuppressWarnings("unchecked")
    public void readData(String path) {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray dataList = (JSONArray) obj;
            // System.out.println(employeeList);

            // Iterate over employee array
            dataList.forEach(emp -> {
                kingList.add(new King().parseDataObject((JSONObject) emp));
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // System.out.println(kingList.get(0).getMieuHieu());
    }

}
