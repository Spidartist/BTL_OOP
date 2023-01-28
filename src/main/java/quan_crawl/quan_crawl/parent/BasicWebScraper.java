package quan_crawl.quan_crawl.parent;
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
	public Document getDoc() {
		return doc;
	}
	protected void setUrl(String url) {
		this.url = url;
	}
	
	protected void connect() {
		try {
			this.doc = Jsoup.connect(this.url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
