package jSoupWebCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerPrototype {
	public static void main(String[] args) {
		String url = "https://openedu.vn/Kho-tri-thuc/Tom-luoc-lich-su-Viet-Nam-qua-cac-thoi-dai?fbclid=IwAR0lTz08KnBFQwd0lprhSTm0bSuWnFFvPy3cTLuVP2JQF74lK_Hg2lhc5Ag";
		Document doc = getUrl(url);
		List<String> titles = new ArrayList<String>();
		Elements elements = doc.select("p[style*=\"line-height:1.380000000000000\"]");
		for (Element e: elements) {
			String title = e.select("span[style*=\"font-weight: 700\"]").text();
			titles.add(title);
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			for (String t: titles) {
				writer.write(t+"\n");
			}
//			System.out.println("Triệu Đá");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Document getUrl(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void writeFile(String title) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
			writer.write("\n"+title);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
