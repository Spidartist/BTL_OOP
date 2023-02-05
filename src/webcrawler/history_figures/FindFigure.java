package webcrawler.history_figures;

import org.jsoup.Jsoup;

import java.awt.desktop.ScreenSleepEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import webcrawler.parent.BasicWebScraper;
import webcrawler.tojson.ICombine;
import webcrawler.tojson.IWriteJson;
import objects.figure.Figure;

public class FindFigure implements ICombine,IWriteJson {
	private ArrayList<Figure> list = new ArrayList<Figure>();
	public static void main(String[] args) {
		FindFigure findFigure = new FindFigure();
		findFigure.combine();
		try {
			findFigure.writeJSon();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String filePath = "D:\\webCrawler\\webcrawler\\src\\webcrawler\\jsonFiles\\figure.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() {
		AnhHung anhHung = new AnhHung();
		TuongNhaTran nhaTran = new TuongNhaTran();
		nhaTran.scraping();
		AnhHungLucLuong anhHungLucLuong = new AnhHungLucLuong();
		anhHung.scraping();
		CacTrangNguyen trangNguyen = new CacTrangNguyen();
		trangNguyen.scraping();
		list.addAll(anhHung.getList());
		list.addAll(anhHungLucLuong.getList());
		list.addAll(trangNguyen.getList());
		list.addAll(nhaTran.getList());
	}
}
