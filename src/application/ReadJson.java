package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import objects.figure.King;

public class ReadJson {
    private ArrayList<King> kingList = new ArrayList<King>();

    public ArrayList<King> getKingList() {
        return kingList;
    }

    @SuppressWarnings("unchecked")
    public void readData() {
        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("D:/HK2022.1/OOP/MidtermPrj/BTL_OOP/king.json")) {
            // Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            // System.out.println(employeeList);

            // Iterate over employee array
            employeeList.forEach(emp -> {
                kingList.add(parseEmployeeObject((JSONObject) emp));
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

    private static King parseEmployeeObject(JSONObject king) {
        // Get employee object within list

        // Get employee first name
        String paperURL = (String) king.get("paperURL");
        // System.out.println(paperURL);
        String mieuHieu = (String) king.get("mieuHieu");
        String thuyHieu = (String) king.get("thuyHieu");
        String nienHieu = (String) king.get("nienHieu");
        String tenHuy = (String) king.get("tenHuy");
        String theThu = (String) king.get("theThu");
        String namTriVi = (String) king.get("namTriVi");
        String ten = (String) king.get("ten");
        King king1 = new King(paperURL, mieuHieu, thuyHieu, nienHieu, tenHuy, theThu, namTriVi, ten);
        return king1;
    }

}
