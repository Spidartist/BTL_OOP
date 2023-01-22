package jSoupWebCrawler.history_figures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import jSoupWebCrawler.parent.BasicWebScraper;
import jSoupWebCrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import objects.figure.HistoricalFigure;
import objects.figure.King;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class TuongNhaTran extends BasicWebScraper implements IScraping {
	private ArrayList<Figure> list = new ArrayList<Figure>();
	public ArrayList<Figure> getList(){
		return this.list;
	}
	public TuongNhaTran() {
		String url = "http://www.quan8.hochiminhcity.gov.vn/dantaphaibietsuta/lists/posts/post.aspx?Source=/dantaphaibietsuta&Category=Nh%C3%A2n+v%E1%BA%ADt+l%E1%BB%8Bch+s%E1%BB%AD+t%E1%BB%AB+th%E1%BA%BF+k%E1%BB%B7+X+%C4%91%E1%BA%BFn+XV&ItemID=83&Mode=1";
		setUrl(url);
		connect();
	}
	@Override
	public void scraping() {
		Element mainDiv = this.doc.getElementById("part1");
		Element mainTable = mainDiv.getElementsByTag("table").first();
		Element paragraphs = mainTable.select("div.ExternalClass6BD0A3317E7F4013AACBBE0FE404A173").first();
		Elements mainDataParagraphs = paragraphs.select("p:contains( )");
		Elements figureParagraph = paragraphs.select("p:has(b)");
		int start = 0;
		int end = 1;
		

		for (Element p: figureParagraph) {
			Elements header = p.getElementsByTag("b");
			String ten = header.text();
			if (!ten.equals("")) {
				int index = ten.indexOf(" ");
				ten = ten.substring(index);
				System.out.println(ten);
				if (ten.contains("(")) {
					index = ten.indexOf("(");
					String nam = ten.substring(index);
					ten= ten.substring(0,index);
					nam = nam.replace("(", "");
					nam = nam.replace(")", "");
					index = nam.indexOf("-");
					String namSinh = nam.substring(0,index);
					String namMat = nam.substring(index+1);
					System.out.println(namSinh + "+" + namMat);
					Figure figure = new Figure(ten,namSinh,namMat);
					list.add(figure);
				}
				else {
					System.out.println(ten);
					Figure figure = new Figure(ten);
					list.add(figure);
				}
			}
		}

	}

}
