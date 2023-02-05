package webcrawler.festival;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LeHoiAnGiang extends BasicFindFestival {

	public LeHoiAnGiang() {
		String url = "https://angiangtourist.vn/thoi-gian-va-dia-diem-to-chuc-cac-le-hoi-lon-o-an-giang/#";
		this.url = url;
		connect();
	}

	@Override
	public void scraping() {
		Element mainContent = this.doc.getElementsByClass("entry-content single-page").first();
		Elements paragraphs = mainContent.getElementsByTag("p");
		for (int i = 6; i <= 13; i++) {
			Element p = paragraphs.get(i);
			Element header = p.getElementsByTag("strong").first();
			// Festival festival = new Festival();
			if (header != null) {
				String content = p.html();
				String tenLeHoi = header.text();
				System.out.println(tenLeHoi);
				// System.out.println(content);
				int start = content.indexOf("<br>") + 4;
				String mainData = content.substring(start);
				String[] data = mainData.split("<br>");
				// System.out.println(data.length);
				String thoiGian = "";
				String diaDiem = "";
				String nhanVat = "";
				String noiDung = "";
				for (String d : data) {
					String[] dataParts = d.split(": ");
					String tieuDe = "";
					String noiDungChinh = "";
					tieuDe = tieuDe.concat(dataParts[0]);
					noiDungChinh = noiDungChinh.concat(dataParts[1]);
					System.out.println(tieuDe);
					System.out.println(noiDungChinh);
					switch (tieuDe) {
						case " Thời gian": {
							thoiGian = thoiGian.concat(noiDungChinh);
							thoiGian = thoiGian.trim();
							break;
						}
						case " Địa điểm": {
							diaDiem = diaDiem.concat(noiDungChinh);
							diaDiem = diaDiem.trim();
							break;
						}
						case " Đối tượng suy tôn": {
							if (noiDungChinh.contains("Trực")) {
								int index = noiDungChinh.indexOf(",");
								noiDungChinh = noiDungChinh.substring(0, index);
							} else if (noiDungChinh.contains("Cảnh")) {
								int index = noiDungChinh.indexOf("(");
								noiDungChinh = noiDungChinh.substring(0, index);
							}
							nhanVat = nhanVat.concat(noiDungChinh);
							nhanVat = nhanVat.trim();
							break;
						}
						case " Đặc điểm": {
							noiDung = noiDung.concat(noiDungChinh);
							noiDung = noiDung.trim();
							break;
						}
					}

				}
				Festival festival = new Festival(tenLeHoi, thoiGian, diaDiem);
				festival.setNoiDung(noiDung);
				Figure figure = new Figure(nhanVat);
				festival.setFigure(figure);
				list.add(festival);
			}
		}
	}

	public static void main(String[] args) {
		LeHoiAnGiang anGiang = new LeHoiAnGiang();
		anGiang.scraping();
		String filePath = "D:\\webCrawler\\webcrawler\\src\\webcrawler\\jsonFiles\\festival.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<Festival> festivals = anGiang.getList();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(festivals, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
