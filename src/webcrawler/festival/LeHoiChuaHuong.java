package webcrawler.festival;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.festival.Festival;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LeHoiChuaHuong extends BasicWebScraper implements IScraping {
	private Festival chuaHuong;

	public Document getDoc() {
		return this.doc;
	}

	public LeHoiChuaHuong() {
		String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_ch%C3%B9a_H%C6%B0%C6%A1ng";
		this.url = url;
		connect();
	}

	public Festival getChuaHuong() {
		return this.chuaHuong;
	}

	@Override
	public void scraping() {
		Element main = this.doc.getElementsByTag("main").first();
		Element name = main.getElementsByTag("h1").first();
		String tenLeHoi = name.text();
		Element mainContent = main.select("div#mw-content-text").first();
		Element firstPara = mainContent.getElementsByTag("p").first();
		Elements attributes = firstPara.getElementsByTag("a");
		// System.out.println(attributes.size());
		String diaDiem = new String();
		for (int i = 0; i < 2; i++) {
			diaDiem = diaDiem.concat(attributes.get(i).text());
			diaDiem = diaDiem.concat(" ");
		}
		Element secondPara = mainContent.select("p:contains(" + "từ mùng" + ")").first();
		String thoiGian = secondPara.text();
		String noiDung = firstPara.text();
		chuaHuong = new Festival(tenLeHoi, thoiGian, diaDiem);
		chuaHuong.setNoiDung(noiDung);
		System.out.println(noiDung);
	}

	public static void main(String[] args) {
		LeHoiChuaHuong obj = new LeHoiChuaHuong();
		obj.scraping();
	}
}
