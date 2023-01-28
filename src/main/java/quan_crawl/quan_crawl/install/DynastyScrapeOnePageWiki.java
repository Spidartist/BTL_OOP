package quan_crawl.quan_crawl.install;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import quan_crawl.quan_crawl.parent.BasicWebScraper;
import quan_crawl.quan_crawl.parent.IScraping;

public class DynastyScrapeOnePageWiki extends BasicWebScraper implements IScraping {
	private ArrayList<String> kingNames;
	
	public void scraping() {
		Elements kings = this.getDoc().select("#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr");
		boolean flag = false;
		int cntFlag = 0;
		for (Element e: kings) {
			Elements aData = e.getElementsByTag("a");
			Elements tdData = e.getElementsByTag("td");
			Elements thData = e.getElementsByTag("th");
			
//			System.out.println(aData.text());
			if ((aData.size() > 0 && (aData.text().equals("Hoàng đế") || aData.text().equals("Vua"))) || thData.text().equals("Hoàng Đế")) {
//				System.out.println("a");
				flag = true;
				continue;
			}
			if (flag && tdData.text().equals("")) {
				cntFlag += 1;
				if (cntFlag == 2) {
					flag = false;
				}
			
			}
			if (aData.size() > 0 && flag && !aData.get(aData.size()-1).text().equals("") && !aData.get(aData.size()-1).text().matches(".*[0-9].*")) {
				kingNames.add(aData.get(aData.size()-1).text());
			}
		}
	}
	
	public DynastyScrapeOnePageWiki(String url) {
		this.kingNames = new ArrayList<String>();
		this.setUrl(url);
		connect();
	}

	public ArrayList<String> getKingNames() {
		return kingNames;
	}

	public static void main(String[] args) {
		DynastyScrapeOnePageWiki one = new DynastyScrapeOnePageWiki("https://vi.wikipedia.org/wiki/Nh%C3%A0_Nguy%E1%BB%85n");
		one.scraping();
	}

}
