package webcrawler.dynasty;

import java.util.ArrayList;
import org.jsoup.Jsoup;

import java.awt.desktop.ScreenSleepEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;

public class DynastyScrapeVYCTKings extends BasicWebScraper implements IScraping {

	public DynastyScrapeVYCTKings() {
		String url = "http://vietycotruyen.com.vn/cac-trieu-dai-viet-nam-qua-tung-thoi-ky-lich-su";
		this.setUrl(url);
		connect();
	}

	public void scraping() {
		ArrayList<String> rawData = new ArrayList<String>();
		Elements trData = this.getDoc().select("tr");
		for (Element e : trData) {
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
