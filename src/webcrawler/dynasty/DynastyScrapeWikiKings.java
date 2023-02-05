package webcrawler.dynasty;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.dynasty.Dynasty;
import webcrawler.parent.BasicFind;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class DynastyScrapeWikiKings extends BasicFind {
	
	private LinkedList<String> blackList;
	private LinkedList<Dynasty> dynastys;

	public void scraping() {
		DynastyScrapeName names;
		names = new DynastyScrapeName();
		names.scraping();
		
		Elements canCrawlNames = this.getDoc()
				.select("#mw-content-text > div.mw-parser-output > ul:nth-child(6) > li > a");
		for (Element e : canCrawlNames) {
			if (names.getDynasty_names().contains(e.text())) {
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

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	public DynastyScrapeWikiKings() {
		String url = "https://vi.wikipedia.org/wiki/Tri%E1%BB%81u_%C4%91%E1%BA%A1i";
		this.setUrl(url);
		this.blackList = new LinkedList<String>();
		this.blackList.add("Nhà Lý");
		this.blackList.add("Nhà Trần");
		this.blackList.add("Nhà Hậu Lê");
		this.blackList.add("Nhà Nguyễn");

		connect();
		this.dynastys = new LinkedList<Dynasty>();
		
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
