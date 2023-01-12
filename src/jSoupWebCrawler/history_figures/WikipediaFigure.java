package jSoupWebCrawler.history_figures;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
public class WikipediaFigure extends BasicWebScraper implements IScraping {
	private ArrayList<String> figures = new ArrayList<String>();
	public WikipediaFigure() {
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connect();
	}
	
	public Document getDoc() {
		return this.doc;
	}
	@Override
	public void scraping() {
		Element mainPage = this.doc.getElementsByClass("container textview").get(0);
//		Elements headers = mainPage.getElementsByTag("h2");
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
	}
	
	public static void main(String[] args) {
		WikipediaFigure obj = new WikipediaFigure();
		ArrayList<King> kings = new ArrayList<King>();
		Document doc = obj.getDoc();
		Elements firstTable = doc.select("table.toccolours");
		Elements tables = doc.select("table");
		for (Element t : firstTable) {
			if (tables.contains(t)) {
				tables.remove(t);
			}
		}
		int lastTable = tables.size()-1;
		tables.remove(lastTable);
		String strRegEx= "<sup[^?]*";
		String attrRegEx = "<a[^?]*";
		String openRegEx = "[1-9]*";
		for (Element table : tables) {
			Elements rows = table.select("tr");
			for (Element row:rows) {
				Elements datas = row.getElementsByTag("td");
				if (datas.size() >= 10 && datas.size() < 18) {
					String name = datas.get(1).text();
//					name = name.replaceAll(openRegEx, "");
					King king = new King(name);
					Element attr = datas.get(1).select("a").first();
					if (attr != null) {
						String url = attr.attr("href");
						System.out.println(url);
						king.setPaperURL(url);
					}
					
					String mieuHieu = datas.get(2).text();
//					mieuHieu = mieuHieu.replaceAll(openRegEx,"");

					king.setMieuHieu(mieuHieu);
					String thuyHieu = datas.get(3).text();
//					thuyHieu = thuyHieu.replaceAll(openRegEx,"");
					
					king.setThuyHieu(thuyHieu);
					String nienHieu = datas.get(4).text();
//					nienHieu = nienHieu.replaceAll(openRegEx,"");
					
					king.setNienHieu(nienHieu);
					String tenHuy = datas.get(5).text();
					king.setTenHuy(tenHuy);
//				tenHuy = tenHuy.replaceAll(openRegEx,"");
					
					String theThu = datas.get(6).text();
					theThu.replace("[^\\[a-z\\]]", "");
					theThu = theThu.replaceAll(strRegEx,"");
					theThu = theThu.replaceAll(attrRegEx,"");
					king.setTheThu(theThu);
					String namTriVi = datas.get(7).html();
					
					namTriVi = namTriVi.replaceAll(strRegEx, "");
					String ngang = datas.get(8).text();
					String end = datas.get(9).html();
					end = end.replaceAll(strRegEx,"");
//					end = end.replaceAll(strRegEx, "");
					namTriVi = namTriVi.concat(ngang);
					namTriVi = namTriVi.concat(end);
					namTriVi = namTriVi.replaceAll(attrRegEx, "");
//					System.out.println(namTriVi);
					king.setNamTriVi(namTriVi);
					kings.add(king);
				}
				else continue;
			}
		}
		String filePath = "./king.json";
		JSONArray jarray = new JSONArray();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String test = gson.toJson(kings);
//		System.out.println(test);
		try {
            FileWriter writer = new FileWriter(new File(filePath));
            gson.toJson(kings, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
