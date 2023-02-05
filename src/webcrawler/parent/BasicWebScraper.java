package webcrawler.parent;

import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.nodes.Document;

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
			doc = Jsoup.connect(this.url).timeout(10*1000).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
