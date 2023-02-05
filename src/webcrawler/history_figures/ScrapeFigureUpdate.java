package webcrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import application.readdata.ReadData;
import javafx.collections.ObservableList;
import webcrawler.combine.ICombineData;
import webcrawler.tojson.IWriteJson;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ScrapeFigureUpdate implements IWriteJson, ICombineData {
	private LinkedList<Figure> list = new LinkedList<Figure>();

	public static void main(String[] args) {
		ScrapeFigureUpdate figure = new ScrapeFigureUpdate();
		figure.combine();
		try {
			figure.writeJSon();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String replaceTrieuDai(String original) {
		String trieuDai = "";
		switch (original) {
			case "Bắc thuộc lần 1": {
				trieuDai = trieuDai.concat("Nhà Triệu");
				break;
			}
			case "Trưng Nữ Vương": {
				trieuDai = trieuDai.concat("Hai Bà Trưng");
				break;
			}
			case "Nhà Tiền Lý, Triệu": {
				trieuDai = trieuDai.concat("Nhà Tiền Lý");
				break;
			}
			case "Hậu Trần": {
				trieuDai = trieuDai.concat("Nhà Hậu Trần");
				break;
			}
			case "Trịnh - Nguyễn": {
				trieuDai = trieuDai.concat("Nhà Hậu Lê");
				break;
			}
			case "Triều Lê Sơ": {
				trieuDai = trieuDai.concat("Nhà Lê sơ");
				break;
			}
			case "Nam - Bắc Triều": {
				trieuDai = trieuDai.concat("Nhà Mạc");
				break;
			}
			case "Nhà Nguyễn độc lập": {
				trieuDai = trieuDai.concat("Nhà Nguyễn");
				break;
			}
			case "Pháp đô hộ": {
				trieuDai = trieuDai.concat("Đế quốc Việt Nam");
				break;
			}
			case "Nước Việt Nam mới": {
				trieuDai = trieuDai.concat("Việt Nam Dân chủ Cộng hòa");
				break;
			}
			case "Dựng nước": {
				trieuDai = trieuDai.concat("Hồng Bàng thị");
				break;
			}
			default: {
				trieuDai = trieuDai.concat(original);
			}
		}
		return trieuDai;
	}

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

	@Override
	public void combine() {
		int pageIndex = 1901;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";
		int lastIndex = 2300;
		while (pageIndex <= lastIndex) {
			String url = urlFirstHalf + Integer.toString(pageIndex);
			VanSu vanSu = new VanSu(url);
			vanSu.scraping();
			list.add(vanSu.getFigure());
			pageIndex += 1;
		}

		System.out.println("num of mem: " + list.size());
		for (Figure figure : list) {
			ArrayList<Dynasty> dynastyList = figure.getTrieuDai();
			for (Dynasty dynasty : dynastyList) {
				String name = dynasty.getName();
				dynasty.setName(replaceTrieuDai(name));
			}
		}
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\data\\figureUpdate.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ObservableList<Figure> listObservablesFigure = new ReadData<Figure>()
				.FromJsonToArray("src/data/figureUpdate.json", Figure.class);
		LinkedList<Figure> originalList = new LinkedList<Figure>();
		for (int i = 0; i < listObservablesFigure.size(); i++) {
			originalList.add(listObservablesFigure.get(i));
		}
		originalList.addAll(list);
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(originalList, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
