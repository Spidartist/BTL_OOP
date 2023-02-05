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

public class TranDanhLon extends BasicWebScraper implements IScraping {
	private ArrayList<SuKien> TranDanh = new ArrayList<SuKien>();

	public ArrayList<SuKien> getList() {
		return TranDanh;
	}

	TranDanhLon() {
		String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_tr%E1%BA%ADn_%C4%91%C3%A1nh_trong_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		this.url = url;
		connect();
	}

	// kiem tra xem trong phan element co chua 3 hoac 4 ki tu chu so lien tuc hay
	// khong
	private String CaoThoiGian(String DuLieuTho) {
		DuLieuTho = DuLieuTho.replaceAll("[^0-9]", "#");
		String[] arr = DuLieuTho.split("#");

		boolean LaMotNam = false;
		int SoNamTrongDuLieu = 0;
		StringBuilder ThoiGianTraVe = new StringBuilder();

		for (String s : arr) {
			if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$")) {
				LaMotNam = true;
				SoNamTrongDuLieu++;
				if (SoNamTrongDuLieu > 1)
					ThoiGianTraVe.append(" - ");
				ThoiGianTraVe.append(s);
			}
		}

		if (!LaMotNam)
			return "";
		return ThoiGianTraVe.toString();
	}

	// private String LayNam(String thoi_gian) {
	// 	thoi_gian = thoi_gian.replaceAll("[^0-9]", "#");
	// 	String[] arr = thoi_gian.split("#");
	// 	for (String s : arr) {
	// 		if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$")) {
	// 			return s;
	// 		}
	// 	}
	// 	return "";
	// }

	private String CaoDiaDiem(String DuLieuTho, String KyTuCanXoa) {
		// StringBuilder s = new StringBuilder();
		final String[] tukhoa = new String[] { "Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh " };
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

	public void scraping() {
		String TamNhoGiaTriThoiGian = "";
		Element noi_dung_chinh = this.doc.getElementById("bodyContent");
		Elements danh_sach_tran_danh = noi_dung_chinh.select("li");
		for (Element e : danh_sach_tran_danh) {
			String tran_danh = e.text();
			if (tran_danh.contains("1988")) // cao bang tay =))
				break;
			String thoi_gian = CaoThoiGian(e.text());
			if (thoi_gian.length() != 0) {
				TamNhoGiaTriThoiGian = thoi_gian;
			}
			SuKien s = new SuKien();
			s.setTen(tran_danh);
			s.setThoi_gian(TamNhoGiaTriThoiGian);
			s.setDia_diem(CaoDiaDiem(tran_danh, TamNhoGiaTriThoiGian));
			s.setNhan_vat_lien_quan(CaoNhanVat(tran_danh, TamNhoGiaTriThoiGian));
			s.getNhan_vat_lien_quan();
			TranDanh.add(s);
			System.out.println(TamNhoGiaTriThoiGian + ": " + tran_danh);
		}

	}

	public static void main(String args[]) {
		TranDanhLon trandanh = new TranDanhLon();
		trandanh.scraping();
		String JsonURL = "src\\objects\\event\\TranDanhLon.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JsonURL));
			ArrayList<SuKien> DanhSachTranDanh = new ArrayList<SuKien>();
			DanhSachTranDanh.addAll(trandanh.getList());
			gson.toJson(DanhSachTranDanh, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}