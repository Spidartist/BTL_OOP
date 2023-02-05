package objects.figure;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

// import javafx.scene.chart.Axis.TickMark;

import objects.dynasty.Dynasty;

public class Figure extends HistoricalFigure {
	private String queQuan;
	private String danToc;
	private String namNhapNgu;
	private String ghiChu;
	private String namDoTrangNguyen;
	private String tenKhac;
	private King doiVua;

	private ArrayList<Dynasty> trieuDai = new ArrayList<Dynasty>();

	public void setTenKhac(String tenKhac) {
		this.tenKhac = tenKhac;
	}

	public String getTenKhac() {
		return tenKhac;
	}
	// public Figure(String ten, String namSinh, String namMat, String queQuan,
	// String danToc, String namNhapNgu,
	// String ghiChu, String namDoTrangNguyen, King doiVua) {
	// super(ten, namSinh, namMat);
	// this.queQuan = queQuan;
	// this.danToc = danToc;
	// this.namNhapNgu = namNhapNgu;
	// this.ghiChu = ghiChu;
	// this.namDoTrangNguyen = namDoTrangNguyen;
	// this.doiVua = doiVua;
	// }

	public Figure(String ten, String namSinh, String namMat, String queQuan, String danToc, String namNhapNgu,
			String ghiChu, String namDoTrangNguyen) {
		super(ten, namSinh, namMat);
		this.queQuan = queQuan;
		this.danToc = danToc;
		this.namNhapNgu = namNhapNgu;
		this.ghiChu = ghiChu;
		this.namDoTrangNguyen = namDoTrangNguyen;

	}

	// public Figure(String queQuan, String danToc, String namNhapNgu, String
	// ghiChu, String namDoTrangNguyen,
	// King doiVua) {
	// this.queQuan = queQuan;
	// this.danToc = danToc;
	// this.namNhapNgu = namNhapNgu;
	// this.ghiChu = ghiChu;
	// this.namDoTrangNguyen = namDoTrangNguyen;
	// this.doiVua = doiVua;
	// }

	public Figure(String ten, String queQuan, String danToc, String namNhapNgu, String ghiChu,
			String namDoTrangNguyen) {
		super(ten);
		this.queQuan = queQuan;
		this.danToc = danToc;
		this.namNhapNgu = namNhapNgu;
		this.ghiChu = ghiChu;
		this.namDoTrangNguyen = namDoTrangNguyen;
	}

	public Figure(String ten, String namSinh, String namMat, String queQuan, String ghiChu, String tenKhac,
			ArrayList<Dynasty> trieuDai) {
		super(ten, namSinh, namMat);
		this.queQuan = queQuan;
		this.ghiChu = ghiChu;
		this.tenKhac = tenKhac;
		this.trieuDai = trieuDai;
	}

	public Figure() {
	}

	public String getNamDoTrangNguyen() {
		return namDoTrangNguyen;
	}

	public void setNamDoTrangNguyen(String namDoTrangNguyen) {
		this.namDoTrangNguyen = namDoTrangNguyen;
	}

	public King getDoiVua() {
		return doiVua;
	}

	public void setDoiVua(King doiVua) {
		this.doiVua = doiVua;
	}

	public Figure(String ten) {
		super(ten);
	}

	public Figure(String ten, String namSinh, String namMat) {
		super(ten, namSinh, namMat);
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public String getDanToc() {
		return danToc;
	}

	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}

	public String getNamNhapNgu() {
		return namNhapNgu;
	}

	public void setNamNhapNgu(String namPhongChuc) {
		this.namNhapNgu = namPhongChuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public ArrayList<Dynasty> getTrieuDai() {
		return trieuDai;
	}

	public void setTrieuDai(ArrayList<Dynasty> trieuDai) {
		this.trieuDai = trieuDai;
	}


}
