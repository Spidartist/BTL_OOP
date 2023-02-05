package webcrawler.relic;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.relic.Relic;
import webcrawler.parent.BasicFind;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class RelicScrapeVHHNOnePage extends BasicFind{
	private LinkedList<Relic> relics;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetFigure = 0;
	
	public LinkedList<Relic> getRelics() {
		return relics;
	}
	
	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int getLienKetFigure() {
		return lienKetFigure;
	}

	public RelicScrapeVHHNOnePage(int i) {
		relics = new LinkedList<Relic>();
		String baseUrl = "http://ditichlichsu-vanhoahanoi.com/category/2dtlsvh/page/";
		String url = baseUrl + Integer.toString(i) + "/";
		this.setUrl(url);
		connect();
	}

	public void scraping() throws IOException {
		Elements aData = this.getDoc().select("div#post-wrapper > div > article > a");
		System.out.println(aData.text());
		for (Element e: aData) {
			String boxUrl = e.attr("href");
			System.out.println(boxUrl);
			RelicScrapeVHHNOneBox h = new RelicScrapeVHHNOneBox(boxUrl);
			h.scraping();
			lienKetDynasty += h.getLienKetDynasty();
			lienKetKing += h.getLienKetKing();
			lienKetFigure += h.getLienKetFigure();
			relics.add(h.getRelic());
		}
	}
	
	public static void main(String[] args) throws IOException {
		RelicScrapeVHHNOnePage r = new RelicScrapeVHHNOnePage(1);
		r.scraping();
	}
}
