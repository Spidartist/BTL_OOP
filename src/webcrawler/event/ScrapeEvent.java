package webcrawler.event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import objects.event.SuKien;
import webcrawler.combine.ICombineData;
import webcrawler.tojson.IWriteJson;

public class ScrapeEvent implements IWriteJson, ICombineData {
	private LinkedList<SuKien> list = new LinkedList<SuKien>();

	public static void main(String[] args) {
		ScrapeEvent event = new ScrapeEvent();
		event.combine();
		try {
			event.writeJSon();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() {
		SuKienLonTuWiki sukien = new SuKienLonTuWiki();
		sukien.scraping();
		TranDanhLon trandanh = new TranDanhLon();
		trandanh.scraping();
		list.addAll(sukien.getList());
		list.addAll(trandanh.getList());
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String JsonURL = "src\\data\\event.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JsonURL));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
