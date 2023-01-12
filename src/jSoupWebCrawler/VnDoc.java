package jSoupWebCrawler;
import org.jsoup.Jsoup;

import java.awt.desktop.ScreenSleepEvent;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import objects.dynasty.HistoricalDynasty;
public class VnDoc {
	public static void main(String[] args) {
		String url = "https://vndoc.com/lich-su-viet-nam";
		ArrayList<String> figures = new ArrayList<String>();
		Document doc;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
			Element mainPage = doc.getElementsByClass("container textview").get(0);
//			Elements headers = mainPage.getElementsByTag("h2");
			Elements paragraphs = mainPage.getElementsByTag("p");
			for (Element p: paragraphs) {
				String context = p.html();
				if (context.contains("<br>")) {
					String[] t = context.split("<br>");
					for (String tmp : t) {
						if (tmp.contains("(") && tmp.length() < 80) {
							figures.add(tmp);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String t: figures) {
			System.out.println(t);
		}
		
	}
}
