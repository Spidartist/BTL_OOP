package webcrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;
import objects.figure.King;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VanSuMain {
	public static void main(String[] args) {
		ArrayList<String> links = new ArrayList<String>();
		int pageIndex = 1;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";
		ArrayList<Figure> list = new ArrayList<Figure>();
		while (pageIndex <= 200) {
			String url = urlFirstHalf + Integer.toString(pageIndex);
			VanSu vanSu = new VanSu(url);
			vanSu.scraping();
			list.add(vanSu.getFigure());
			pageIndex += 1;
		}
		System.out.println("num of mem: " + list.size());
		// for (String link : links) {
		// System.out.println(link);
		// }
	}
}
