package webcrawler.festival;

import java.util.ArrayList;

import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import org.jsoup.select.Elements;

public class LeHoiBacNinh extends BasicFindFestival {
	
	public LeHoiBacNinh() {
		String url = "https://alltours.vn/bac-ninh/nhung-le-hoi-o-bac-ninh.html";
		this.url = url;
		connect();
	}

	@Override
	public void scraping() {
		Element mainContent = this.doc.getElementsByClass("single-blog-content entry clr").first();
		Elements festivalNames = mainContent.select("h2");
		Elements paragraphs = mainContent.select("p:has(br)");
		for (int i = 0; i < festivalNames.size(); i++) {
			Element p = paragraphs.get(i);
			String tenLeHoi = festivalNames.get(i).text();
			tenLeHoi = tenLeHoi.replaceAll("\\d{1,2}. ", "");
			System.out.println(tenLeHoi);
			String thoiGian = "";
			String diaDiem = "";
			String nhanVat = "";
			String noiDung = "";
			if (p != null) {
				String content = p.html();
				content = content.replace("hoàng:", "hoàng");
				String[] data = content.split("<br>");
				for (String d : data) {
					System.out.println(d);
					String[] dataParts = d.split(": ");
					String tieuDe = "";
					String noiDungChinh = "";
					tieuDe = tieuDe.concat(dataParts[0]);
					noiDungChinh = noiDungChinh.concat(dataParts[1]);
					System.out.println(tieuDe);
					System.out.println(noiDungChinh);
					switch (tieuDe) {
						case "Thời gian": {
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
							if (noiDungChinh.contains("Truyền")) {
								int index = noiDungChinh.indexOf("Nguyễn");
								noiDungChinh = noiDungChinh.substring(index);
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
			}
			Festival festival = new Festival(tenLeHoi, thoiGian, diaDiem);
			festival.setNoiDung(noiDung);
			Figure figure = new Figure(nhanVat);
			festival.setFigure(figure);
			list.add(festival);
		}
	}
}
