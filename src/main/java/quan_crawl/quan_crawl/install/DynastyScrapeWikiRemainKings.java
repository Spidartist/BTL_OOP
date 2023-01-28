package quan_crawl.quan_crawl.install;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import quan_crawl.quan_crawl.parent.BasicWebScraper;
import quan_crawl.quan_crawl.parent.IScraping;

public class DynastyScrapeWikiRemainKings extends BasicWebScraper implements IScraping{
	
	
	
	public DynastyScrapeWikiRemainKings() {
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		
		Elements tableClassElements = this.getDoc().select("table");
		System.out.println(tableClassElements.size());
		Elements h3Elements = this.getDoc().select("h3");
		for (Element e: h3Elements) {
			Node tableKings = e.nextElementSibling();
			if (tableKings != null) {
				while (tableKings.nodeName() != "table") {
					tableKings = tableKings.nextSibling();
				}
				System.out.println(tableKings.nodeName());
			}
			
			Elements remainedKings = e.select("> span:nth-child(2)");
			System.out.println(remainedKings.text().split("và")[0].split(" \\(")[0] + "*");
		}
		
	}
	
	public static void main(String[] args) {
		DynastyScrapeWikiRemainKings d = new DynastyScrapeWikiRemainKings();
		d.scraping();
	}

}
