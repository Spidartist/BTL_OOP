package webcrawler.festival;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import org.jsoup.nodes.Element;

public class LeHoiGoDongDa extends BasicWebScraper implements IScraping {
	private Festival goDongDa;

	public LeHoiGoDongDa() {
		String url = "http://lehoi.cinet.vn/Pages/ArticleDetail.aspx?siteid=1&sitepageid=282&articleid=811";
		this.url = url;
		connect();
	}

	public Festival getGoDongDa() {
		return this.goDongDa;
	}

	@Override
	public void scraping() {
		Element paragraph = this.doc.select("p.MsoNormal").first();
		String mainText = paragraph.text();
		System.out.println(mainText);
		// String[] contents = mainText.split(".",2);
		int endTenLeHoi = mainText.indexOf("diễn");
		String tenLeHoi = mainText.substring(0, endTenLeHoi);
		System.out.println(tenLeHoi);

		int startThoiGian = mainText.indexOf("vào");
		int endThoiGian = mainText.indexOf("tại");
		String thoiGian = mainText.substring(startThoiGian, endThoiGian);
		System.out.println(thoiGian);

		int endDiaDiem = mainText.indexOf(". ");
		String diaDiem = mainText.substring(endThoiGian, endDiaDiem);
		System.out.println(diaDiem);

		int startFigure = mainText.indexOf("vua");
		int endFigure = mainText.indexOf(" - ");
		String nameFigure = mainText.substring(startFigure, endFigure);
		System.out.println(nameFigure);
		Figure king = new Figure(nameFigure);

		goDongDa = new Festival(tenLeHoi, thoiGian, diaDiem);
		goDongDa.setFigure(king);
		String noiDung = mainText.substring(endDiaDiem + 2, mainText.length());
		goDongDa.setNoiDung(noiDung);
	}

	public static void main(String[] args) {
		LeHoiGoDongDa dongDa = new LeHoiGoDongDa();
		dongDa.scraping();
	}
}
