package webcrawler.relic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class RelicScrapeDiTichOneBox extends BasicWebScraper implements IScraping {

	public RelicScrapeDiTichOneBox(String url) {
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		String name = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > h2").text();
		System.out.println(name);
		
		String address = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__sidebar > div:nth-child(1) > section > div > div > div.hl__contact-info__address > span").text();
		System.out.println(address);
		
		StringBuilder personB = new StringBuilder();
		StringBuilder rankB = new StringBuilder();
		StringBuilder typeB = new StringBuilder();
		Elements info = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section  div > span:nth-child(2)");
		for (Element e: info) {
			if (e.text() != "") {
//				System.out.println(e.text());
				if (e.text().contains("Loại hình di tích")) {
					typeB.append(e.text());
				}else if (e.text().contains("Xếp hạng")){
					rankB.append(e.text());
				}else if (e.text().contains("Đối tượng thờ")) {
					personB.append(e.text());
				}
			}
		}
		String type;
		String rank;
		String person;
		
		if (typeB.length() == 0){
			type = "Unknown";
		}else {
			type = typeB.toString().replace("Loại hình di tích: ", "");
		}
		
		if (rankB.length() == 0){
			rank = "Unknown";
		}else {
			rank = rankB.toString().replace("Xếp hạng: ", "");
		}
		
		if (personB.length() == 0){
			person = "Unknown";
		}else {
			person = personB.toString().replace("Đối tượng thờ: ", "");
		}
		
		System.out.println(person);
		System.out.println(rank);
		System.out.println(type);
		
		
	}
	
	public static void main(String[] args) {
		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
		for (int i = 1865;i<=6139;i++) {
			System.out.println(i);
			String url = baseUrl + Integer.toString(i);
			RelicScrapeDiTichOneBox r = new RelicScrapeDiTichOneBox(url);
//			System.out.println(i);
			r.scraping();
		}
		
	}

}
