package webcrawler.dynasty;

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

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.dynasty.Dynasty;
import objects.figure.Figure;

public class DynastyScrapeWikiKings extends BasicWebScraper implements IScraping {
	private DynastyScrapeName names;
	private ArrayList<String> blackList;
	private ArrayList<Dynasty> dynastys;

	public void scraping() {
		Elements canCrawlNames = this.getDoc()
				.select("#mw-content-text > div.mw-parser-output > ul:nth-child(6) > li > a");
		for (Element e : canCrawlNames) {
			if (this.names.getDynasty_names().contains(e.text())) {
				if (!this.blackList.contains(e.text())) {
					Dynasty dynasty = new Dynasty(e.text());
					String absHref = e.attr("abs:href");
					DynastyScrapeOnePageWiki onePage = new DynastyScrapeOnePageWiki(absHref);
					onePage.scraping();
					dynasty.setKings(onePage.getKingNames());
					dynastys.add(dynasty);
					System.out.println(e.text());
				}
			}
		}
	}

	public ArrayList<Dynasty> getDynastys() {
		return dynastys;
	}

	public DynastyScrapeWikiKings() {
		String url = "https://vi.wikipedia.org/wiki/Tri%E1%BB%81u_%C4%91%E1%BA%A1i";
		this.setUrl(url);
		this.blackList = new ArrayList<String>();
		this.blackList.add("Nhà Lý");
		this.blackList.add("Nhà Trần");
		this.blackList.add("Nhà Hậu Lê");
		this.blackList.add("Nhà Nguyễn");

		connect();
		this.dynastys = new ArrayList<Dynasty>();
		this.names = new DynastyScrapeName();
		this.names.scraping();
		// for (String t: this.names.getDynasty_names()) {
		// System.out.println(t);
		// }
		// System.out.println(this.names.getDynasty_names().size());
	}

	public static void main(String[] args) {
		DynastyScrapeWikiKings dynastyScrapeWiki = new DynastyScrapeWikiKings();
		dynastyScrapeWiki.scraping();

	}
}