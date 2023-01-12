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
public class TomTatLichSu {
	
	
	public static void main(String[] args) {
		String url = "https://nguoikesu.com/tu-lieu/tom-luoc-lich-su-viet-nam";
		Document doc;
		ArrayList<HistoricalDynasty> list = new ArrayList<HistoricalDynasty>();
		try {
			doc = Jsoup.connect(url).get();
			Element mainTag = doc.getElementsByClass("item-page").get(0);
			Elements mainParagraphs = mainTag.getElementsByTag("p");
			
			for (Element t: mainParagraphs ) {
				Element time = t.getElementsByTag("strong").get(0);
				HistoricalDynasty infor = new HistoricalDynasty(time.text());
				Elements links = t.select("a[href]");
				if (links != null) {
					for (Element link : links) {
						infor.setNhanVat(link.text());
					}
				}
				list.add(infor);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (HistoricalDynasty l : list) {
			System.out.println(l.getNienDai());
		}
	}
	
	public static void getInfor(Element firstPara) {
		String name = firstPara.select("b").first().text();
		String text = firstPara.text();
		System.out.println(text);
		System.out.println(name);
		int start = text.indexOf(", ");
		int end = text.indexOf(" - ");
		int close = text.indexOf(")");
		String namSinh = text.substring(start+2, end);
		String namMat = text.substring(end+3,close);
		System.out.println(namSinh+".");
		System.out.println(namMat+".");		
	}
}
