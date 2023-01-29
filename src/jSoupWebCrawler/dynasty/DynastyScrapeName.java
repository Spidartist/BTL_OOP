package jSoupWebCrawler.dynasty;

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

import jSoupWebCrawler.parent.BasicWebScraper;
import jSoupWebCrawler.parent.IScraping;
import objects.figure.Figure;
public class DynastyScrapeName extends BasicWebScraper implements IScraping {
	
	private ArrayList<String> dynasty_names;
	private ArrayList<String> black_list;
	
	public DynastyScrapeName() {
		dynasty_names = new ArrayList<String>();
		black_list = new ArrayList<String>();
		black_list.add("Bắc thuộc lần I");
		black_list.add("Bắc thuộc lần II");
		black_list.add("Nhà Lý & Nhà Triệu");
		black_list.add("Bắc thuộc lần III");
		black_list.add("Thời kỳ xây nền tự chủ");
		black_list.add("Bắc thuộc lần IV");
		black_list.add("Trịnh Nguyễn Phân Tranh");
		black_list.add("Những cuộc khởi nghĩa chống thực dân Pháp");
		black_list.add("Pháp Thuộc");			
		black_list.add("Kháng chiến chống Pháp");
		black_list.add("Kháng chiến chống Mĩ");
		black_list.add("Khởi nghĩa Bà Triệu");
		black_list.add("Tự chủ");
		black_list.add("Khởi nghĩa Lam Sơn");
		black_list.add("Loạn 12 sứ quân");
		black_list.add("phân tranh");
		black_list.add("Pháp thuộc");
		black_list.add("Vua Việt Nam");
		black_list.add("Nguyên thủ Việt Nam");
		black_list.add("Các vương quốc cổ");
		black_list.add("Niên biểu lịch sử Việt Nam");
		black_list.add("sửa");
		black_list.add("Lê");
		black_list.add("trung");
		black_list.add("hưng");
		black_list.add("Trịnh");
		black_list.add("Nguyễn");
		black_list.add("Triệu Việt Vương");
		black_list.add("Mai Hắc Đế");
		black_list.add("Phùng Hưng");
		black_list.add("Họ Khúc");
		black_list.add("Dương Đình Nghệ");
		black_list.add("Kiều Công Tiễn");
		black_list.add("An Dương Vương");
		black_list.add("Hồng Bàng");
		black_list.add("Chiến tranh Đông Dương");
		
		String url = "https://vi.wikipedia.org/wiki/Ph%C3%A1p_thu%E1%BB%99c";
		this.setUrl(url);
		connect();
	}
	
	public ArrayList<String> getDynasty_names() {
		return dynasty_names;
	}

	public void scraping() {
		Elements names = this.getDoc().select("#mw-content-text > div.mw-parser-output > table.table.toccolours > tbody > tr:nth-child(2) > td > table > tbody > tr > td  a");
		
		// Lay ten tat ca cac trieu dai tu trang Wiki
		for (Element e: names) {
			if (!this.black_list.contains(e.text()) && !e.text().matches(".*[0-9].*")) {
				if (!this.dynasty_names.contains(e.text())) {
					this.dynasty_names.add(e.text().replaceAll("\n", " "));
				}
			}
		}
		this.dynasty_names.add("Hồng Bàng thị");
		this.dynasty_names.add("Nhà Lê trung hưng");
		this.dynasty_names.add("Chúa Trịnh");
		this.dynasty_names.add("Chúa Nguyễn");
		
//		System.out.println(this.dynasty_names.size());
	}
	public static void main(String[] args) {
		DynastyScrapeName dynastyScrape = new DynastyScrapeName();
		dynastyScrape.scraping();
	}
}
