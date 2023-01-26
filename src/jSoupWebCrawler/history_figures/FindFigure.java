package jSoupWebCrawler.history_figures;

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

import jSoupWebCrawler.parent.BasicWebScraper;
import objects.figure.Figure;

public class FindFigure {
	public static void main(String[] args) {
		AnhHung anhHung = new AnhHung();
		anhHung.scraping();
		TuongNhaTran nhaTran = new TuongNhaTran();
		nhaTran.scraping();
		AnhHungLucLuong anhHungLucLuong = new AnhHungLucLuong();
		anhHung.scraping();
		CacTrangNguyen trangNguyen = new CacTrangNguyen();
		trangNguyen.scraping();
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\jSoupWebCrawler\\jsonFiles\\figure.json";
		ArrayList<Figure> list = new ArrayList<Figure>();
		list.addAll(anhHungLucLuong.getList());
		list.addAll(trangNguyen.getList());
		list.addAll(nhaTran.getList());
		list.addAll(anhHung.getList());
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
