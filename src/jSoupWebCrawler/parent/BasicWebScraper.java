package jSoupWebCrawler.parent;

import org.jsoup.Jsoup;

import java.awt.desktop.ScreenSleepEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class BasicWebScraper {
	protected String url;
	protected Document doc;
	
	public BasicWebScraper() {
	}
	protected void setUrl(String url) {
		this.url = url;
	}
	
	protected void connect() {
		try {
			doc = Jsoup.connect(this.url).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
