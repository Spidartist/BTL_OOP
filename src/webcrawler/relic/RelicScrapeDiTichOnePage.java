package webcrawler.relic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import webcrawler.parent.BasicFind;

public class RelicScrapeDiTichOnePage extends BasicFind {
	private String type;
	private String rank;
	private String person;
	private String name;
	private String address;
	
	public RelicScrapeDiTichOnePage(String url) {
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		this.name = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > h2").text();
		
		this.address = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__sidebar > div:nth-child(1) > section > div > div > div.hl__contact-info__address > span").text();

		
		StringBuilder personB = new StringBuilder();
		StringBuilder rankB = new StringBuilder();
		StringBuilder typeB = new StringBuilder();
		Elements info = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section  div > span:nth-child(2)");
		for (Element e: info) {
			if (e.text() != "") {
				if (e.text().contains("Loại hình di tích")) {
					typeB.append(e.text());
				}else if (e.text().contains("Xếp hạng")){
					rankB.append(e.text());
				}else if (e.text().contains("Đối tượng thờ")) {
					personB.append(e.text());
				}
			}
		}
		
		
		if (typeB.length() == 0){
			this.type = "Unknown";
		}else {
			this.type = typeB.toString().replace("Loại hình di tích: ", "");
		}
		
		if (rankB.length() == 0){
			this.rank = "Unknown";
		}else {
			this.rank = rankB.toString().replace("Xếp hạng: ", "");
		}
		
		if (personB.length() == 0){
			this.person = "Unknown";
		}else {
			this.person = personB.toString().replace("Đối tượng thờ: ", "");
		}
		
		
//		System.out.println(person);
//		System.out.println(rank);
//		System.out.println(type);
		
		
	}
	
	public String getType() {
		return type;
	}

	public String getRank() {
		return rank;
	}

	public String getPerson() {
		return person;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public static void main(String[] args) {
		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
		for (int i = 1865;i<=6139;i++) {
			System.out.println(i);
			String url = baseUrl + Integer.toString(i);
			RelicScrapeDiTichOnePage r = new RelicScrapeDiTichOnePage(url);
			r.scraping();
		}
		
	}

}
