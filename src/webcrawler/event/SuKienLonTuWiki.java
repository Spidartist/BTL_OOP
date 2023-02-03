package webcrawler.event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import webcrawler.parent.*;
import objects.event.*;

public class SuKienLonTuWiki extends BasicWebScraper implements IScraping {
	private ArrayList<SuKien> SuKienWiki = new ArrayList<SuKien>();
	public ArrayList<SuKien> getList(){
		return SuKienWiki;
	}
	
	SuKienLonTuWiki(){
		String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		this.url = url;
		connect();
	}
	
	private String ChuaChuSo(String DuLieuTho) {
		char[] chars = DuLieuTho.toCharArray();
		for(char c: chars) {
			if(Character.isDigit(c))
				return DuLieuTho;
		}
		return "";
	}
	
	private String CaoThoiGian(String DuLieuTho) {
		DuLieuTho = DuLieuTho.replaceAll("[^0-9]", "#");
		String[] arr = DuLieuTho.split("#");
		
		boolean LaMotNam = false;
		int SoNamTrongDuLieu = 0;
		StringBuilder ThoiGianTraVe = new StringBuilder(); 
		
		for(String s: arr) {
			if(s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$"))				
				if(s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$")) {
					LaMotNam = true;
					SoNamTrongDuLieu++;
					if(SoNamTrongDuLieu > 1)
						ThoiGianTraVe.append(" - ");
					ThoiGianTraVe.append(s);
				}
			}
			
			if(!LaMotNam)
				return "";
			return ThoiGianTraVe.toString();
	}
	
	public void scraping() {
		String TamNhoGiaTriThoiGian = "";
		Element noi_dung_chinh = this.doc.getElementById("bodyContent"); 
		//Elements thoi_ki = noi_dung_chinh.select("h3");
		Elements su_kien = noi_dung_chinh.select("p, dd");
		for(Element e: su_kien) {
			//mot so su kien ghi ro rang ngay thang nam o tag con thap hon
			String ten = e.select("a").text();//day
			if(ten.length() == 0) {
				TamNhoGiaTriThoiGian = e.select("b").text();
				TamNhoGiaTriThoiGian = ChuaChuSo(TamNhoGiaTriThoiGian);
				continue;
			}
			String thoi_gian = e.select("b").text();
			if (ChuaChuSo(thoi_gian) == "")
				continue;
			if(CaoThoiGian(thoi_gian) == "")
				thoi_gian = thoi_gian.concat(" " + TamNhoGiaTriThoiGian);
			SuKien s = new SuKien();
			s.setTen(ten);
			s.setThoi_gian(thoi_gian);
			
			SuKienWiki.add(s);
			System.out.println(thoi_gian + ": " + ten);
		}
	}
	
	public static void main(String args[]) {
		SuKienLonTuWiki sukien = new SuKienLonTuWiki();
		sukien.Scraping();
		//can chinh sua khi len remote
		String JsonURL = "C:\\Users\\lemin\\OneDrive\\Documents\\New Java projects\\webCrawler\\src\\SuKienLon.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
            FileWriter writer = new FileWriter(new File(JsonURL));
            ArrayList<SuKien> DanhSachSuKien = new ArrayList<SuKien>();
            DanhSachSuKien.addAll(sukien.getList());
            gson.toJson(DanhSachSuKien, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
