package webcrawler.relic;

import org.jsoup.select.Elements;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class RelicScrapeDiTichOnePage extends BasicWebScraper implements IScraping{

	public RelicScrapeDiTichOnePage(String url) {
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		Elements e;
		
	}
	
	public static void main(String[] args) {
		RelicScrapeDiTichOnePage r = new RelicScrapeDiTichOnePage("http://ditich.vn/FrontEnd/DiTich");
	}

}
