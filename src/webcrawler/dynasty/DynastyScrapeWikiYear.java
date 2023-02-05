package webcrawler.dynasty;

import java.util.ArrayList;

import org.jsoup.select.Elements;

import objects.dynasty.Dynasty;
import webcrawler.parent.BasicFind;

public class DynastyScrapeWikiYear extends BasicFind {
	private String tenTrieuDai;
	private String beginYear;
	private String endYear;

	public DynastyScrapeWikiYear(String tenTrieuDai) {
		String url = nameToUrl(tenTrieuDai);
		this.tenTrieuDai = tenTrieuDai;
		this.setUrl(url);
		connect();
	}

	private String nameToUrl(String tenTrieuDaiCu) {
		String baseUrl = "https://vi.wikipedia.org/wiki/";
		String tenTrieuDai;
		if (tenTrieuDaiCu.equals("Bắc thuộc lần I")) {
			tenTrieuDai = "Thời kỳ Bắc thuộc lần thứ nhất";
		}else if (tenTrieuDaiCu.equals("Bắc thuộc lần II")){
			tenTrieuDai = "Thời kỳ Bắc thuộc lần thứ hai";
		}else if (tenTrieuDaiCu.equals("Bắc thuộc lần III")){
			tenTrieuDai = "Thời kỳ Bắc thuộc lần thứ ba";
		}else if (tenTrieuDaiCu.equals("Bắc thuộc lần IV")){
			tenTrieuDai = "Thời kỳ Bắc thuộc lần thứ tư";
		}else {
			tenTrieuDai = tenTrieuDaiCu;
		}
		String[] arrOfStr = tenTrieuDai.split(" ");
		StringBuffer b = new StringBuffer();
		b.append(baseUrl);
		for (int i = 0; i < arrOfStr.length; i++) {
			b.append(arrOfStr[i]);
			if (i != arrOfStr.length - 1) {
				b.append("_");
			}
		}
		String url = b.toString();
		return url;
	}

	public String getTenTrieuDai() {
		return tenTrieuDai;
	}

	public String getBeginYear() {
		return beginYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void scraping() {
		// System.out.println(this.url);
		String allYears;
		if (this.tenTrieuDai.equals("Cộng hòa Xã hội Chủ nghĩa Việt Nam")) {
			allYears = "1976–nay";
		} else if (this.tenTrieuDai.equals("Thời tiền sử")) {
			allYears = "đầu–3100 TCN";
		} else if (this.tenTrieuDai.equals("Hai Bà Trưng")) {
			Elements years = this.getDoc().select(
					"#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr:nth-child(4) > td > a");
			beginYear = years.get(0).attr("title");
			endYear = years.get(1).attr("title");
			allYears = beginYear + "–" + endYear;
		} else if (this.tenTrieuDai.equals("Nhà Trần")) {
			Elements years = this.getDoc().select(
					"#mw-content-text > div.mw-parser-output > div.mw-stack.stack-container.stack-right > div:nth-child(1) > table > tbody > tr:nth-child(3) > td");
			allYears = years.text();
		} else if (this.tenTrieuDai.equals("Nhà Hậu Lê")) {
			allYears = "1427–1789";
		} else if (this.tenTrieuDai.equals("Họ Khúc")) {
			allYears = "923–930";
		} else if (this.tenTrieuDai.equals("Hồng Bàng thị")) {
			allYears = "2879 TCN–258 TCN";
		} else if (this.tenTrieuDai.equals("Tự chủ")) {
			allYears = "905–938";
		} else if (this.tenTrieuDai.equals("Nhà Thục")) {
			allYears = "257 TCN–179 TCN";
		} else if (this.tenTrieuDai.equals("Bắc thuộc lần IV")) {
			allYears = "1407–1427";
		} else {
			Elements years = this.getDoc()
					.select("#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr:nth-child(2) > td");
			if (!years.text().matches(".*[0-9].*")) {
				years = this.getDoc().select(
						"#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr:nth-child(3) > td");
			}
			allYears = years.text().split(" [0-9]")[0];
		}
		String[] arrOfStr = allYears.split("–");
		if (arrOfStr.length == 1) {
			beginYear = arrOfStr[0];
			endYear = arrOfStr[0];
		} else {
			beginYear = arrOfStr[0];
			endYear = arrOfStr[1];
		}
		// System.out.println(beginYear);
		// System.out.println(endYear);
	}

	public static void main(String[] args) {
		ArrayList<Dynasty> dynastys = new ArrayList<Dynasty>();
		DynastyScrapeName names = new DynastyScrapeName();
		names.scraping();
		for (String e : names.getDynasty_names()) {
			DynastyScrapeWikiYear y = new DynastyScrapeWikiYear(e);
			y.scraping();
			Dynasty dynasty = new Dynasty(y.getBeginYear(), y.getEndYear(), y.getTenTrieuDai());
			dynastys.add(dynasty);
		}
		for (Dynasty d : dynastys) {
			System.out.println(d.getName() + " " + d.getStartYear() + " " + d.getEndYear());
		}

	}
}
