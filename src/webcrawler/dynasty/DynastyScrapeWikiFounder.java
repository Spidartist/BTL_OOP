package webcrawler.dynasty;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.dynasty.Dynasty;
import objects.figure.King;
import webcrawler.parent.BasicFind;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class DynastyScrapeWikiFounder extends BasicFind {
	private LinkedList<Dynasty> dynastys;

	public DynastyScrapeWikiFounder() {
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
		this.dynastys = new LinkedList<Dynasty>();
		
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	public void scraping() {
		DynastyScrapeName names;
		names = new DynastyScrapeName();
		names.scraping();
		Elements data = this.getDoc().select("#mw-content-text > div.mw-parser-output > table > tbody > tr");
		for (Element e : data) {
			Elements dynasty_name = e.select("td:nth-child(1) > a");
			Elements founder_name = e.select("td:nth-child(2) > a:nth-child(1)");
			if (names.getDynasty_names().contains(dynasty_name.text())) {
				Dynasty dynasty = new Dynasty(dynasty_name.text());
				dynasty.setFounder(new King(founder_name.text()));
				System.out.println(dynasty_name.text());
				this.dynastys.add(dynasty);
			}

			Dynasty d = new Dynasty("Hai Bà Trưng");
			d.setFounder(new King("Trưng Trắc"));
			this.dynastys.add(d);
			d = new Dynasty("Nhà Lê sơ");
			d.setFounder(new King("Lê Lợi"));
			this.dynastys.add(d);
			d = new Dynasty("Nhà Lê trung hưng");
			d.setFounder(new King("Lê Duy Ninh"));
			this.dynastys.add(d);
		}

	}

	public static void main(String[] args) {
		DynastyScrapeWikiFounder w = new DynastyScrapeWikiFounder();
		w.scraping();
	}
}
