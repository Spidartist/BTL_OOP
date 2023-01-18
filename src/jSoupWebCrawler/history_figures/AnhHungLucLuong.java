package jSoupWebCrawler.history_figures;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import jSoupWebCrawler.parent.BasicWebScraper;
import jSoupWebCrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import objects.figure.HistoricalFigure;
import objects.figure.King;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
public class AnhHungLucLuong extends BasicWebScraper implements IScraping {
	private ArrayList<Figure> list = new ArrayList<Figure>();
	public ArrayList<Figure> getList() {
		return list;
	}
	public AnhHungLucLuong() {
		String url = "https://doanhnghiepvn.vn/kham-pha/chan-dung-10-anh-hung-trong-khang-chien-chong-phap/20200130024940748";
		setUrl(url);
		connect();
	}
	@Override
	public void scraping() {
		Element mainContent = doc.getElementById("abody");
		Elements span = mainContent.select("span:contains(sinh)");
		for (Element e: span) {
			System.out.println(e.text());
		}
	}
}
