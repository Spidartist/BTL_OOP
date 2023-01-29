package jSoupWebCrawler.festival;

import jSoupWebCrawler.parent.BasicWebScraper;
import jSoupWebCrawler.parent.IScraping;
import objects.festival.Festival;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jSoupWebCrawler.parent.BasicWebScraper;
import jSoupWebCrawler.parent.IScraping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
public class FindFestival extends BasicWebScraper implements IScraping {
	public FindFestival() {
		String url = "https://vinpearl.com/vi/top-12-le-hoi-mua-xuan-dac-sac-o-3-mien";
		this.url = url;
		connect();
	}
	
	public Document getDoc() {
		return this.doc;
	}
	@Override
	public void scraping() {
		
	}
	public static void main(String[] args) {
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
		ArrayList<Festival> list = new ArrayList<Festival>();
		list.addAll(tuyenQuang.getList());
		list.addAll(bacNinh.getList());
		list.addAll(anGiang.getList());
		list.addAll(daNang.getList());
		list.addAll(obj.getList());
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\jSoupWebCrawler\\jsonFiles\\festival.json";
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
