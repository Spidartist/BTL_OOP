package webcrawler.festival;

import java.util.ArrayList;

import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import org.jsoup.select.Elements;

public class LeHoiDaNang extends BasicFindFestival {
	

	public LeHoiDaNang() {
		String url = "https://dulichkhampha24.com/le-hoi-o-da-nang.html";
		this.url = url;
		connect();
	}

	@Override
	public void scraping() {
		ArrayList<String> content = new ArrayList<String>();
		Element mainContent = this.doc.getElementsByClass("td-post-content").first();
		Elements festivalNames = mainContent.select("h3");
		Elements dataList = mainContent.select("ul[style*=\"text-align: justify\"]");
		Elements leHoiPhaoHoa = mainContent.select("p:contains(pháo hoa)");
		content.add(leHoiPhaoHoa.text());
		Elements leHoiQuanTheAm = mainContent.select("p:contains(Quán Thế Âm)");
		content.add(leHoiQuanTheAm.text());
		Element leHoiBaNa = mainContent.select("p:contains(Lễ hội Carnival)").last();
		content.add(leHoiBaNa.text());
		Element leHoiCauNgu = mainContent.select("p:contains(làng chài)").first();
		content.add(leHoiCauNgu.text());
		Element leHoiAmNhac = mainContent.select("p:contains(Biển Đông)").first();
		content.add(leHoiAmNhac.text());
		Element leHoiDuaThuyen = mainContent.select("p:contains(hanh thông)").first();
		content.add(leHoiDuaThuyen.text());
		Element leHoiRuocMucDong = mainContent.select("p:contains(bội thu)").first();
		content.add(leHoiRuocMucDong.text());
		Element leHoiLangHoaMy = mainContent.select("p:contains(Hòa Mỹ)").first();
		content.add(leHoiLangHoaMy.text());
		Element leHoiSach = mainContent.select("p:contains(200 gian)").first();
		content.add(leHoiSach.text());
		Element leHoiLangTuyLoan = mainContent.select("p:contains(năm 1999)").first();
		content.add(leHoiLangTuyLoan.text());
		Element leHoiLangAnHai_first = mainContent.select("p:contains(mang màu sắc)").first();
		Element leHoiLangAnHai_second = mainContent.select("p:contains(thuyền tứ linh)").first();
		String anHai = leHoiLangAnHai_first.text().concat(leHoiLangAnHai_second.text());
		content.add(anHai);
		Element leHoiKhinhKhiCau = mainContent.select("p:contains(khinh khí cầu)").first();
		content.add(leHoiKhinhKhiCau.text());
		Element leHoiHoaBaNa = mainContent.select("p:contains(hoa đẹp)").first();
		content.add(leHoiHoaBaNa.text());
		int numberOfFestival = festivalNames.size();
		for (int i = 0; i < numberOfFestival; i++) {
			String tenLeHoi = festivalNames.get(i).text();
			tenLeHoi = tenLeHoi.replaceAll("\\d{1,2}. ", "");
			if (tenLeHoi.contains(" - ")) {
				int index = tenLeHoi.indexOf("-");
				tenLeHoi = tenLeHoi.substring(0, index);
				tenLeHoi = tenLeHoi.trim();
			}
			System.out.println(tenLeHoi);
			String thoiGian = "";
			String diaDiem = "";
			String noiDung = content.get(i);
			String nhanVat = "";
			Elements data = dataList.get(i).select("li");
			for (Element d : data) {
				String infor = d.text();
				String[] splitString = infor.split(":");
				String tieuDe = splitString[0];
				String noiDungChinh = splitString[1];
				if (tieuDe.equals("Địa điểm")) {
					diaDiem = diaDiem.concat(noiDungChinh);
				} else if (tieuDe.equals("Thời gian")) {
					thoiGian = thoiGian.concat(noiDungChinh);
				}
			}
			System.out.printf("%s %s %s\n", thoiGian, diaDiem, noiDung);
			Festival festival = new Festival(tenLeHoi, thoiGian, diaDiem);
			festival.setNoiDung(noiDung);
			Figure figure = new Figure(nhanVat);
			festival.setFigure(figure);
			list.add(festival);
		} // end for 2
	}

}
