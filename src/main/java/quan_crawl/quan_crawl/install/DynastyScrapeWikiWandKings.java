package quan_crawl.quan_crawl.install;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import quan_crawl.quan_crawl.parent.BasicWebScraper;
import quan_crawl.quan_crawl.parent.IScraping;

public class DynastyScrapeWikiWandKings extends BasicWebScraper implements IScraping{
	private String tenTrieuDai;
	private ArrayList<String> kings;
	
	public String getTenTrieuDai() {
		return tenTrieuDai;
	}

	public DynastyScrapeWikiWandKings(String tenTrieuDai) {
		this.tenTrieuDai = tenTrieuDai;
		this.kings = new ArrayList<String>();
		String url = "https://www.wikiwand.com/vi/B%E1%BA%A3n_m%E1%BA%ABu:Danh_s%C3%A1ch_vua_v%C3%A0_ho%C3%A0ng_%C4%91%E1%BA%BF_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		Elements trData = this.getDoc().select("#content-root > article > span > div > table > tbody > tr");
		for (Element e: trData) {
			Elements thData = e.select("> th > a");
			Elements tdData = e.select("> td  a");
			if (thData.text().equals(this.tenTrieuDai) || (this.tenTrieuDai.equals("Nhà Hậu Lê") && thData.text().equals("Nhà Lê trung hưng"))) {
//				System.out.println(this.tenTrieuDai);
				for (Element e1: tdData) {
					kings.add(e1.text());
				}
//				System.out.println(kings.size());
			}
		}
		
	}
	
	
	public ArrayList<String> getKings() {
		return kings;
	}

	public static void main(String[] args) {
		ArrayList<String> remained = new ArrayList<String>();
		remained.add("Hai Bà Trưng");
		remained.add("Nhà Lý");
		remained.add("Nhà Trần");
		remained.add("Nhà Hậu Trần");
		remained.add("Nhà Hậu Lê");
		remained.add("Nhà Lê sơ");
		remained.add("Nhà Nguyễn");
		remained.add("Hồng Bàng thị");
		remained.add("Nhà Lê trung hưng");
		remained.add("Chúa Trịnh");
		remained.add("Chúa Nguyễn");
		for (String s: remained) {
			DynastyScrapeWikiWandKings d = new DynastyScrapeWikiWandKings(s);
			d.scraping();
		}
		
	}
}
