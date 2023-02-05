package webcrawler.history_figures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;
import objects.figure.King;

import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CacTrangNguyen extends BasicWebScraper implements IScraping {

	private ArrayList<Figure> list = new ArrayList<Figure>();

	public CacTrangNguyen() {
		String url = "https://modacaocap.com/danh-sach-trang-nguyen-viet-nam/";
		this.url = url;
		connect();
	}

	public ArrayList<Figure> getList() {
		return list;
	}

	@Override
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

	public static void main(String[] args) {

		CacTrangNguyen trangNguyen = new CacTrangNguyen();
		trangNguyen.scraping();
		String filePath = "D:\\webCrawler\\webcrawler\\src\\webcrawler\\jsonFiles\\figure.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			ArrayList<Figure> list = new ArrayList<Figure>();
			list.addAll(trangNguyen.getList());
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
