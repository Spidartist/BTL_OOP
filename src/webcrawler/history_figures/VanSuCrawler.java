package webcrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VanSuCrawler {
	public static void main(String[] args) {
//		int pageIndex = 1901;
//		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";
//		LinkedList<Figure> list = new LinkedList<Figure>();
//		int lastIndex = 2300;
//		while (pageIndex <= lastIndex) {
//			String url = urlFirstHalf + Integer.toString(pageIndex);
//			VanSu vanSu = new VanSu(url);
//			vanSu.scraping();
//			list.add(vanSu.getFigure());
//			pageIndex += 1;
//		}
//
//		System.out.println("num of mem: " + list.size());
//		for (Figure figure:list) {
//			ArrayList<Dynasty> dynastyList = figure.getTrieuDai();
//			for (Dynasty dynasty : dynastyList) {
//				String name = dynasty.getName();
//				dynasty.setName(replaceTrieuDai(name));
//			}
//		}
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\data\\figureUpdate.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JSONArray figureDataList = readData("src/data/figureUpdate.json");
		LinkedList<Figure> figureList = new LinkedList<Figure>();
		for (int i = 0; i < figureDataList.size(); i++) {
            figureList.add(new Figure().parseDataObject((JSONObject) figureDataList.get(i)));
        }
		
		JSONArray dynastyDataList = readData("src/data/dynastys.json");
		LinkedList<Dynasty> dynastyList = new LinkedList<Dynasty>();
		for (int i = 0; i < dynastyDataList.size(); i++) {
            dynastyList.add(new Dynasty().parseDataObject((JSONObject) dynastyDataList.get(i)));
        }
		System.out.println(dynastyList.size());
		System.out.println(figureList.size());
		
		for (int i=0;i<figureList.size();i++) {
			ArrayList<Dynasty> dynasty = figureList.get(i).getTrieuDai();
			for (int j=0;j<dynasty.size();j++) {
				String name = dynasty.get(j).getName();
				System.out.println(name);
				Dynasty matchedDynasty = findDynasty(name, dynastyList);
				
				if (matchedDynasty != null) {
					Dynasty tmp = dynasty.remove(j);
					dynasty.add(j,matchedDynasty);
				}
			}
			figureList.get(i).setTrieuDai(dynasty);
		}

		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(figureList, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String replaceTrieuDai(String original) {
		String trieuDai = "";
		switch(original) {
			case "Bắc thuộc lần 1":{
				trieuDai = trieuDai.concat("Nhà Triệu");
				break;
			}
			case "Trưng Nữ Vương":{
				trieuDai = trieuDai.concat("Hai Bà Trưng");
				break;
			}
			case "Nhà Tiền Lý, Triệu":{
				trieuDai = trieuDai.concat("Nhà Tiền Lý");
				break;
			}
			case "Hậu Trần":{
				trieuDai = trieuDai.concat("Nhà Hậu Trần");
				break;
			}
			case "Trịnh - Nguyễn":{
				trieuDai = trieuDai.concat("Nhà Hậu Lê");
				break;
			}
			case "Triều Lê Sơ":{
				trieuDai = trieuDai.concat("Nhà Lê sơ");
				break;
			}
			case "Nam - Bắc Triều":{
				trieuDai = trieuDai.concat("Nhà Mạc");
				break;
			}
			case "Nhà Nguyễn độc lập":{
				trieuDai = trieuDai.concat("Nhà Nguyễn");
				break;
			}
			case "Pháp đô hộ":{
				trieuDai = trieuDai.concat("Đế quốc Việt Nam");
				break;
			}
			case "Nước Việt Nam mới":{
				trieuDai = trieuDai.concat("Việt Nam Dân chủ Cộng hòa");
				break;
			}
			case "Dựng nước":{
				trieuDai = trieuDai.concat("Hồng Bàng thị");
				break;
			}
			default:{
				trieuDai = trieuDai.concat(original);
			}
		}
		return trieuDai;
	}
	@SuppressWarnings("unchecked")
    public static JSONArray readData(String path) {
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
	
	public static Dynasty findDynasty(String name,LinkedList<Dynasty> dynastyList) {
		for (int i=0;i<dynastyList.size();i++) {
			if(dynastyList.get(i).getName().contains(name)) {
				return dynastyList.get(i);
			}
		}
		return null;
	}
}

