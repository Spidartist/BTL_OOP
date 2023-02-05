package webcrawler.history_figures;

import java.util.ArrayList;

import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;
import org.jsoup.select.Elements;

public class AnhHungLucLuong extends BasicWebScraper implements IScraping {
	private ArrayList<Figure> list = new ArrayList<Figure>();

	public ArrayList<Figure> getList() {
		return list;
	}

	public AnhHungLucLuong() {
		String url = "https://doanhnghiepvn.vn/kham-pha/chan-dung-10-anh-hung-trong-khang-chien-chong-phap/20200130024940748";
		setUrl(url);
		connect();
	}

	@Override
	public void scraping() {
		Element mainContent = doc.getElementById("abody");
		Elements paragraphs = mainContent.select("span:contains(sinh)");
		for (Element e : paragraphs) {
			String data = e.text();
			System.out.println(data);
			int index = data.indexOf(".");
			String infor = data.substring(0, index);
			if (infor.contains("Bế Văn Đàn")) {
				infor = infor.replace("Bế Văn Đàn, dân tộc Tày, sinh năm 1931",
						"Bế Văn Đàn sinh năm 1931, dân tộc Tày");
				infor = infor.replace(" tại", ", quê ở");
			}
			System.out.println(infor);
			int endName = infor.indexOf(" sinh");
			String ten = infor.substring(0, endName);
			System.out.println(ten);
			int startDOB = infor.indexOf("1");
			String namSinh = infor.substring(startDOB, startDOB + 4);
			System.out.println(namSinh);
			int startQueQuan = infor.indexOf("ở");
			String queQuan = infor.substring(startQueQuan);
			queQuan = queQuan.replace("ở ", "");
			System.out.println(queQuan);
			int startDanToc = infor.indexOf("dân");
			int endDanToc = infor.indexOf(", q");
			String danToc = infor.substring(startDanToc, endDanToc);
			danToc = danToc.replace("dân tộc ", "");
			System.out.println(danToc);
			String namNhapNgu = "";
			if (infor.contains("nhập ngũ")) {
				int startNamNhapNgu = infor.indexOf("nhập");
				namNhapNgu = namNhapNgu.concat(infor.substring(startNamNhapNgu));
				namNhapNgu = namNhapNgu.replace("nhập ngũ ", "");
				System.out.println(namNhapNgu);
			}
			Figure figure = new Figure(ten);
			figure.setNamSinh(namSinh);
			figure.setNamMat("Chưa biết");
			figure.setNamNhapNgu(namNhapNgu);
			figure.setDanToc(danToc);
			figure.setQueQuan(queQuan);
			String ghiChu = data.substring(index + 2);
			System.out.println(ghiChu);
			figure.setGhiChu(ghiChu);
			list.add(figure);
		}
	}
}
