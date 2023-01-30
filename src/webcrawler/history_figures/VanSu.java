package webcrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VanSu extends BasicWebScraper implements IScraping {
	private Figure figure;

	public VanSu(String url) {
		setUrl(url);
		connect();
	}

	public Figure getFigure() {
		return figure;
	}

	@Override
	public void scraping() {
		Element nameDiv = this.doc.getElementsByClass("active section").first();
		String name = nameDiv.text();
		Element table = this.doc.select("table").first();
		Element tableBody = table.select("tbody").first();
		Elements rows = tableBody.getElementsByTag("tr");
		figure = new Figure(name);
		System.out.println(name);
		for (Element row : rows) {
			Elements rowData = row.select("td");
			if (rowData.size() != 1) {
				String headline = rowData.get(0).text();
				if (headline.contains("Tên khác")) {
					figure.setTenKhac(rowData.get(1).text());
				} else if (headline.contains("Năm sinh")) {
					String year = rowData.get(1).text();
					int index = year.indexOf("-");
					String namSinh = year.substring(0, index);
					String namMat = year.substring(index + 1);
					figure.setNamSinh(namSinh);
					figure.setNamMat(namMat);
				} else if (headline.contains("Tỉnh thành")) {
					String queQuan = rowData.get(1).text();
					figure.setQueQuan(queQuan);
				} else if (headline.contains("Thời kì")) {
					Elements br = rowData.get(1).getElementsByTag("br");
					if (br.size() > 1) {
						String data = rowData.get(1).text();
						System.out.println(data);
						int index = data.indexOf("-");
						int openIndex = data.indexOf("(");
						if (openIndex != -1) {
							String dynastyData = data.substring(index + 1, openIndex);
							Dynasty dynasty = new Dynasty(dynastyData);
							figure.getTrieuDai().add(dynasty);
						} else {
							String dynastyData = data.substring(index + 1);
							Dynasty dynasty = new Dynasty(dynastyData);
							figure.getTrieuDai().add(dynasty);
						}
					} else {
						String data = rowData.get(1).text();
						System.out.println(data);
						int index = data.indexOf("-");
						String dynastyData = data.substring(index + 1);
						String[] dynasties = dynastyData.split("-");
						for (String d : dynasties) {
							int openIndex = data.indexOf("(");
							if (openIndex != -1) {
								String getDynasty = data.substring(index + 1, openIndex);
								Dynasty dynasty = new Dynasty(getDynasty);
								figure.getTrieuDai().add(dynasty);
							} else {
								String getDynasty = data.substring(index + 1);
								Dynasty dynasty = new Dynasty(getDynasty);
								figure.getTrieuDai().add(dynasty);
							}
						}
					}
				}
			} else {
				Elements paragraphs = rowData.get(0).getElementsByTag("p");
				String ghiChu = "";
				for (Element p : paragraphs) {
					ghiChu = ghiChu.concat(p.text());
				}
				System.out.println(ghiChu);
			}
		}
	}

}
