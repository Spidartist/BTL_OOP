package jSoupWebCrawler.history_figures;
import jSoupWebCrawler.parent.BasicWebScraper;
import jSoupWebCrawler.parent.IScraping;
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
public class VuaNhaTran extends BasicWebScraper implements IScraping {
	private ArrayList<String> figures = new ArrayList<String>();
	public VuaNhaTran() {
		String url = "http://www.quan8.hochiminhcity.gov.vn/dantaphaibietsuta/lists/posts/post.aspx?Source=/dantaphaibietsuta&Category=Nh%C3%A2n+v%E1%BA%ADt+l%E1%BB%8Bch+s%E1%BB%AD+t%E1%BB%AB+th%E1%BA%BF+k%E1%BB%B7+X+%C4%91%E1%BA%BFn+XV&ItemID=82&Mode=1";
		this.setUrl(url);
		connect();
	}
	@Override
	public void scraping() {
		Element mainContent = this.doc.getElementsByClass("ExternalClass2E6A1F217AF0441A9B3EFFC203053A6E").first();
		Elements paragraphs = mainContent.select("p:contains(sinh)");
		for (Element p:paragraphs) {
			System.out.println(p.text());
		}
	}
	
}
