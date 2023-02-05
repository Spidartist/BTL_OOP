package webcrawler.history_figures;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.figure.Figure;
import org.jsoup.select.Elements;

public class AnhHung extends BasicWebScraper implements IScraping {
	private ArrayList<Figure> list = new ArrayList<Figure>();

	public ArrayList<Figure> getList() {
		return list;
	}

	public AnhHung() {
		String url = "https://giaoduc.net.vn/nhung-duong-pho-mang-ten-cac-vi-tuong-linh-va-si-quan-quan-doi-post173227.gd";
		setUrl(url);
		connect();
	}

	@Override
	public void scraping() {
		Element mainBody = this.doc.getElementsByClass("details__content cms-body ").first();
		Elements paragraphs = mainBody.select("p");

		Elements hasNameparagraphs = mainBody.select("p:has(em)");
		int numOfParagraphs = hasNameparagraphs.size();
		List<Element> figureParagraph = hasNameparagraphs.subList(2, numOfParagraphs - 1);
		for (Element p : figureParagraph) {
			String ten = p.select("strong").first().text();
			System.out.println(ten);
			ten = ten.concat(" (");
			Element foundParagraph = findElement(ten, paragraphs);
			if (foundParagraph != null) {
				String data = foundParagraph.text();
				int index = data.indexOf(ten);
				String trimData = data.substring(index);
				System.out.println(trimData);
				String nam = findNamSinh(trimData);
				String queQuan = findQueQuan(trimData);
				System.out.println(queQuan);
				index = nam.indexOf("-");
				String namSinh = nam.substring(0, index);
				String namMat = nam.substring(index + 1);
				System.out.println(namSinh + " " + namMat);
				ten = ten.substring(0, ten.length() - 2);
				Figure figure = new Figure(ten, namSinh, namMat);
				figure.setGhiChu(trimData);
				figure.setQueQuan(queQuan);
				list.add(figure);
			}
		}
	}

	public Element findElement(String findStr, List<Element> figureElement) {
		for (Element p : figureElement) {
			String text = p.text();
			if (text.contains(findStr)) {
				return p;
			}
		}
		return null;
	}

	public String findNamSinh(String data) {
		int start = data.indexOf("(");
		int end = data.indexOf(")");
		if (start != -1 && end != -1) {
			String namSinh = data.substring(start + 1, end);
			return namSinh;
		}
		return null;
	}

	public String findQueQuan(String data) {
		if (data.contains("Hoàng Sâm")) {
			int start = data.indexOf("Quê");
			String queQuan = data.substring(start);
			return queQuan;
		} else if (data.contains("Nguyễn Phúc Lai")) {
			int start = data.indexOf("quê");
			String mainData = data.substring(start);
			int end = mainData.indexOf(".");
			String queQuan = mainData.substring(0, end);
			return queQuan;
		}
		int start = data.indexOf("quê");
		String mainData = data.substring(start);
		int end = mainData.indexOf(";");
		String queQuan = mainData.substring(0, end);
		return queQuan;
	}
}
