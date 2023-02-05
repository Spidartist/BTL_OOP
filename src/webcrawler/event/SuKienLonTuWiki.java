package webcrawler.event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import webcrawler.parent.*;
import objects.event.*;

public class SuKienLonTuWiki extends BasicWebScraper implements IScraping {
	private ArrayList<SuKien> SuKienWiki = new ArrayList<SuKien>();

	public ArrayList<SuKien> getList() {
		return SuKienWiki;
	}

	SuKienLonTuWiki() {
		String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		setUrl(url);
		;
		connect();
	}

	private String ChuaChuSo(String DuLieuTho) {
		char[] chars = DuLieuTho.toCharArray();
		for (char c : chars) {
			if (Character.isDigit(c))
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

		for (String s : arr) {
			if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$"))
				LaMotNam = true;
			SoNamTrongDuLieu++;
			if (SoNamTrongDuLieu > 1)
				ThoiGianTraVe.append(" - ");
			ThoiGianTraVe.append(s);
		}

		if (!LaMotNam)
			return "";
		return ThoiGianTraVe.toString();
	}

	// private String LayNam(String thoi_gian) {
	// thoi_gian = thoi_gian.replaceAll("[^0-9]", "#");
	// String[] arr = thoi_gian.split("#");
	// for (String s : arr) {
	// if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$")) {
	// return s;
	// }
	// }
	// return "";
	// }

	private String CaoDiaDiem(String DuLieuTho, String KyTuCanXoa) {
		// StringBuilder s = new StringBuilder();
		final String[] tukhoa = new String[] { "Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh ", "Văn hóa " };
		boolean CanChinhSua = false;
		DuLieuTho = DuLieuTho.replace(KyTuCanXoa, "");

		for (int i = 0; i < tukhoa.length; i++) {
			if (DuLieuTho.contains(tukhoa[i])) {
				CanChinhSua = true;
				DuLieuTho = DuLieuTho.replace(tukhoa[i], "");
				DuLieuTho = DuLieuTho.replaceAll("[-()]", "");
			}
		}
		if (CanChinhSua)
			return DuLieuTho;
		return "";
	}

	private String CaoNhanVat(String DuLieuTho, String KyTuCanXoa) {
		if (DuLieuTho.contains("Dẹp Loạn")) {
			DuLieuTho = DuLieuTho.replace("Dẹp Loạn ", "");
			DuLieuTho = DuLieuTho.replace(KyTuCanXoa, "");
			return DuLieuTho;
		}
		return "";
	}

	/*
	 * private Dynasty CaoTrieuDai(String thoi_gian) {
	 * int moc = 0, startY = 0, endY = 0;
	 * String JsonURL =
	 * "C:\\Users\\lemin\\OneDrive\\Documents\\New Java projects\\BTL_OOP\\BTL_OOP\\src\\objects\\dynasty\\dynasty.json"
	 * ;
	 * Gson gson = new Gson();
	 * ArrayList<Dynasty> trieu_dai = new ArrayList<Dynasty>();
	 * try {
	 * FileReader reader = new FileReader(JsonURL);
	 * Type type = new TypeToken<ArrayList<Dynasty>>() {}.getType();
	 * trieu_dai = gson.fromJson(reader, type);
	 * }
	 * catch(FileNotFoundException e) {
	 * e.printStackTrace();
	 * }
	 * for(Dynasty d: trieu_dai) {
	 * try {
	 * moc = Integer.parseInt(LayNam(thoi_gian));
	 * startY = Integer.parseInt(d.getStartYear());
	 * endY = Integer.parseInt(d.getEndYear());
	 * }
	 * catch(NumberFormatException e) {
	 * e.printStackTrace();
	 * }
	 * if(moc >= startY && moc <= endY) {
	 * return d;
	 * }
	 * }
	 * return new Dynasty();
	 * }
	 */
	public void scraping() {
		String TamNhoGiaTriThoiGian = "";
		Element noi_dung_chinh = this.doc.getElementById("bodyContent");
		// Elements thoi_ki = noi_dung_chinh.select("h3");
		Elements su_kien = noi_dung_chinh.select("p, dd");
		for (Element e : su_kien) {
			// mot so su kien ghi ro rang ngay thang nam o tag con thap hon
			String ten = e.select("a").text();// day
			if (ten.length() == 0) {
				TamNhoGiaTriThoiGian = e.select("b").text();
				TamNhoGiaTriThoiGian = ChuaChuSo(TamNhoGiaTriThoiGian);
				continue;
			}
			String thoi_gian = e.select("b").text();
			if (ChuaChuSo(thoi_gian) == "")
				continue;
			if (CaoThoiGian(thoi_gian) == "")
				thoi_gian = thoi_gian.concat(" " + TamNhoGiaTriThoiGian);
			SuKien s = new SuKien();
			s.setTen(ten);
			s.setThoi_gian(thoi_gian);
			s.setDia_diem(CaoDiaDiem(ten, thoi_gian));
			s.setNhan_vat_lien_quan(CaoNhanVat(ten, thoi_gian));
			s.getNhan_vat_lien_quan();
			// s.setNien_dai(CaoTrieuDai(thoi_gian));
			SuKienWiki.add(s);
			System.out.println(thoi_gian + ": " + ten);
		}
	}

	public static void main(String args[]) {
		SuKienLonTuWiki sukien = new SuKienLonTuWiki();
		sukien.scraping();
		// can chinh sua khi len remote
		String JsonURL = "src\\objects\\event\\SuKienLon.json";
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