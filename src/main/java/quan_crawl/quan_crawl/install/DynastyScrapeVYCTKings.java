package quan_crawl.quan_crawl.install;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import quan_crawl.quan_crawl.parent.BasicWebScraper;
import quan_crawl.quan_crawl.parent.IScraping;

public class DynastyScrapeVYCTKings extends BasicWebScraper implements IScraping{
	
	
	
	public DynastyScrapeVYCTKings() {
		String url = "http://vietycotruyen.com.vn/cac-trieu-dai-viet-nam-qua-tung-thoi-ky-lich-su";
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		ArrayList<String> rawData = new ArrayList<String>();
		Elements trData = this.getDoc().select("tr");
		for (Element e: trData) {
			Elements tdData = e.select("td");
			if (tdData.size() > 1) {
				if (tdData.hasAttr("rowspan")) {
					System.out.println(tdData.attr("rowspan"));
				}
				if (tdData.get(0).text() != "") {
					System.out.println(tdData.get(0).text());
				}
				
				if (tdData.get(1).text().matches(".*[a-z].*")) {
					System.out.println(tdData.get(1).text());
				}
			}
			
		}
		
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
		DynastyScrapeVYCTKings d = new DynastyScrapeVYCTKings();
		d.scraping();
	}

}
