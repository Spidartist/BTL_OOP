package jSoupWebCrawler.dynasty;
import java.util.ArrayList;
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
import jSoupWebCrawler.parent.IScraping;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
public class DynastyScrapeWikiFounder extends BasicWebScraper implements IScraping {
	private DynastyScrapeName names;
	private ArrayList<Dynasty> dynastys;
	
	public DynastyScrapeWikiFounder() {
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
		this.dynastys = new ArrayList<Dynasty>();
		this.names = new DynastyScrapeName();
		this.names.scraping();
	}
	
	public ArrayList<Dynasty> getDynastys() {
		return dynastys;
	}

	public void scraping() {
		Elements data = this.getDoc().select("#mw-content-text > div.mw-parser-output > table > tbody > tr");
		for (Element e: data) {
			Elements dynasty_name = e.select("td:nth-child(1) > a");
			Elements founder_name = e.select("td:nth-child(2) > a:nth-child(1)");
			if (names.getDynasty_names().contains(dynasty_name.text())) {
				Dynasty dynasty = new Dynasty(dynasty_name.text());
				dynasty.setFounder(founder_name.text());
				System.out.println(dynasty_name.text());
				this.dynastys.add(dynasty);
			}
			
			Dynasty d = new Dynasty("Hai Bà Trưng");
			d.setFounder("Trưng Trắc");
			this.dynastys.add(d);
			d = new Dynasty("Nhà Lê sơ");
			d.setFounder("Lê Lợi");
			this.dynastys.add(d);
			d = new Dynasty("Nhà Lê trung hưng");
			d.setFounder("Lê Duy Ninh");
			this.dynastys.add(d);
		}
		
	}
	
	public static void main(String[] args) {
		DynastyScrapeWikiFounder w = new DynastyScrapeWikiFounder();
		w.scraping();
	}

}
