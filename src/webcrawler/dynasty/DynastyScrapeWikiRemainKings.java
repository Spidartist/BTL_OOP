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
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;

public class DynastyScrapeWikiRemainKings extends BasicWebScraper implements IScraping {

	public DynastyScrapeWikiRemainKings() {
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
	}

	public void scraping() {

		Elements tableClassElements = this.getDoc().select("table");
		System.out.println(tableClassElements.size());
		Elements h3Elements = this.getDoc().select("h3");
		for (Element e : h3Elements) {
			Node tableKings = e.nextElementSibling();
			if (tableKings != null) {
				while (tableKings.nodeName() != "table") {
					tableKings = tableKings.nextSibling();
				}
				System.out.println(tableKings.nodeName());
			}

			Elements remainedKings = e.select("> span:nth-child(2)");
			System.out.println(remainedKings.text().split("v�")[0].split(" \\(")[0] + "*");
		}

	}

	public static void main(String[] args) {
		DynastyScrapeWikiRemainKings d = new DynastyScrapeWikiRemainKings();
		d.scraping();
	}

}