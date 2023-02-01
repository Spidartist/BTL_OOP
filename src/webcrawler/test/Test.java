package webcrawler.test;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;
public class Test {
	private String url;
	private Document doc;
	private ArrayList<Figure> list = new ArrayList<Figure>();
	public Test() {
		String url = "your website's url";
		setUrl(url);
		connect();
	}
	public Document getDoc() {
		return doc;
	}

	protected void setUrl(String url) {
		this.url = url;
	}
	protected void connect() {
		try {
			doc = Jsoup.connect(this.url).timeout(10*1000).userAgent("Mozilla/5.0").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void scraping() {
		Element mainContent = this.doc.getElementsByClass("entry-content single-page").first();
		Element table = mainContent.getElementsByClass("wikitable sortable jquery-tablesorter").first();
		Element tableBody = table.select("tbody").first();
		Elements rows = tableBody.select("tr");
		for (Element row : rows) {
			Elements data = row.select("td");
			String ten = data.get(1).text();
			String time = data.get(2).text();
			String namSinh = "";
			String namMat = "";
			if (time.equals("")) {
				namSinh = namSinh.concat("Chưa biết");
				namMat = namMat.concat("Chưa biết");
			} else {
				int index = time.indexOf("-");
				namSinh = namSinh.concat(time.substring(0, index));
				namMat = namMat.concat(time.substring(index + 1));
				namMat = namMat.trim();
				if (namMat.equals("?")) {
					namMat = namMat.replace("?", "Chưa biết");
				}
			}

			String queQuan = data.get(3).text();
			String namDoTrangNguyen = data.get(4).text();
			String vua = data.get(5).text();
			String ghiChu = data.get(6).text();
			King doiVua = new King(vua);
			Figure trang = new Figure(ten, namSinh, namMat);
			trang.setDoiVua(doiVua);
			trang.setGhiChu(ghiChu);
			trang.setNamDoTrangNguyen(namDoTrangNguyen);
			trang.setQueQuan(queQuan);
			list.add(trang);
			System.out.printf("%s: %s + %s %s %s %s %s\n", ten, namSinh, namMat, queQuan, namDoTrangNguyen, vua,
					ghiChu);
		}
	}
	
}
