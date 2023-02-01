package webcrawler.parent;

import java.io.IOException;

import org.jsoup.Jsoup;
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
			doc = Jsoup.connect(this.url).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
