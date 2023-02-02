package webcrawler.dynasty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.dynasty.Dynasty;
import objects.figure.King;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class DynastyScrapeNKSKings extends BasicWebScraper implements IScraping{
	LinkedList<Dynasty> dynastys = new LinkedList<Dynasty>();
	
	public DynastyScrapeNKSKings() {
		String url = "https://nguoikesu.com/tu-lieu/bang-doi-chieu-cac-trieu-dai-viet-nam-va-cac-trieu-dai-trung-quoc";
		this.setUrl(url);
		this.connect();
	}
	
	@Override
	protected void connect() {
		try {
			doc = Jsoup.connect(this.url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.70").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void scraping() {
		ArrayList<String> remained = new ArrayList<String>();
		remained.add("Việt Nam Dân chủ Cộng hòa");
		remained.add("Cộng hòa Xã hội Chủ nghĩa Việt Nam");
		for (String s: remained) {
			Dynasty d = new Dynasty(s);
			dynastys.add(d);
		}
		Elements trData = this.getDoc().select("#content > div.com-content-article.item-page > div.com-content-article__body > table > tbody > tr");
		ArrayList<Element> els = new ArrayList<Element>();
		for (Element e: trData) {
			for (String s: remained) {
				if (e.text().toLowerCase().replace("oà", "òa").contains(s.toLowerCase())) {
					System.out.println(e.text());
					els.add(e);
				}
			}
		}
		
		for (int i=0;i<dynastys.size();i++) {
			LinkedList<King> kings = new LinkedList<King>();
			Element nextE = els.get(i).nextElementSibling();
			while (!nextE.text().contains(".")) {
				
				if (!nextE.select("a:nth-child(1)").text().equals("")) {
					kings.add(new King(nextE.select("a:nth-child(1)").text()));
				}
				
				nextE = nextE.nextElementSibling();
				if (nextE == null) {
					break;
				}
			}
			dynastys.get(i).setKings(kings);
			System.out.println(dynastys.get(i).getKings().size());
		}
		
		System.out.println(this.dynastys.size());
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	public static void main(String[] args) {
		DynastyScrapeNKSKings n = new DynastyScrapeNKSKings();
		n.scraping();
	}
}
