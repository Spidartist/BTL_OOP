package objects.figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
public class TrangNguyen extends HistoricalFigure {
	private String queQuan;
	private String namDoTrangNguyen;
	private King doiVua;
	private String ghiChu;
	public TrangNguyen(String ten, String namSinh, String namMat) {
		super(ten, namSinh, namMat);
	}
	public String getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
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
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
}
