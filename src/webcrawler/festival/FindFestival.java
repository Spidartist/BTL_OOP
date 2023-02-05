package webcrawler.festival;

import webcrawler.combine.ICombineData;
import webcrawler.tojson.IWriteJson;
import objects.festival.Festival;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FindFestival implements IWriteJson, ICombineData {
	private ArrayList<Festival> list = new ArrayList<Festival>();

	public static void main(String[] args) {
		FindFestival fes = new FindFestival();
		fes.combine();
		try {
			fes.writeJSon();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() {
		LeHoiDaNang daNang = new LeHoiDaNang();
		daNang.scraping();
		LeHoiTuyenQuang tuyenQuang = new LeHoiTuyenQuang();
		tuyenQuang.scraping();
		LeHoiBacNinh bacNinh = new LeHoiBacNinh();
		bacNinh.scraping();
		LeHoiAnGiang anGiang = new LeHoiAnGiang();
		anGiang.scraping();
		Wikipedia obj = new Wikipedia();
		obj.scraping();
		list.addAll(tuyenQuang.getList());
		list.addAll(bacNinh.getList());
		list.addAll(anGiang.getList());
		list.addAll(daNang.getList());
		list.addAll(obj.getList());
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\webcrawler\\jsonFiles\\festival.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
