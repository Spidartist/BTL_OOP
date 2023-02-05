package webcrawler.dynasty;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import webcrawler.parent.BasicFind;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class DynastyScrapeName extends BasicFind {

	private LinkedList<String> dynasty_names;
	private LinkedList<String> black_list;

	public DynastyScrapeName() {
		dynasty_names = new LinkedList<String>();
		black_list = new LinkedList<String>();
		black_list.add("Khởi nghĩa Bà Triệu");
		black_list.add("Khởi nghĩa Lam Sơn");
		black_list.add("Loạn 12 sứ quân");
		black_list.add("phân tranh");
		black_list.add("Pháp thuộc");
		black_list.add("Lê");
		black_list.add("trung");
		black_list.add("hưng");
		black_list.add("Trịnh");
		black_list.add("Nguyễn");
		black_list.add("Triệu Việt Vương");
		black_list.add("Mai Hắc Đế");
		black_list.add("Phùng Hưng");
		black_list.add("Dương Đình Nghệ");
		black_list.add("Kiều Công Tiễn");
		black_list.add("An Dương Vương");
		black_list.add("Hồng Bàng");
		black_list.add("Chiến tranh Đông Dương");

		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
	}

	public LinkedList<String> getDynasty_names() {
		return dynasty_names;
	}

	public void scraping() {
		Elements names = this.getDoc().select(
				"#mw-content-text > div.mw-parser-output > table.table.toccolours > tbody > tr:nth-child(2) > td > table > tbody > tr:not(:nth-child(22)):not(:last-child) > td  a");

		// Lay ten tat ca cac trieu dai tu trang Wiki
		for (Element e : names) {
			if (!this.black_list.contains(e.text()) && !e.text().matches(".*[0-9].*")) {
				if (!this.dynasty_names.contains(e.text())) {
					this.dynasty_names.add(e.text().replaceAll("\n", " "));
//					System.out.println(e.text().replaceAll("\n", " "));
				}
			}
		}
		this.dynasty_names.add("Hồng Bàng thị");
		this.dynasty_names.add("Nhà Lê trung hưng");
		this.dynasty_names.add("Chúa Trịnh");
		this.dynasty_names.add("Chúa Nguyễn");
		this.dynasty_names.add("Nhà Thục");
		
		for (String s: this.dynasty_names) {
			System.out.println(s);
		}
		 System.out.println(this.dynasty_names.size());
	}

	public static void main(String[] args) {
		DynastyScrapeName dynastyScrape = new DynastyScrapeName();
		dynastyScrape.scraping();
	}
	
}
