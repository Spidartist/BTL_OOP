package webcrawler.relic;

import java.io.IOException;
import java.util.LinkedList;

import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;
import objects.relic.Relic;
import webcrawler.linkdata.LinkRelicWithFigureAndDynasty;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public class RelicScrapeVHHNOneBox extends BasicWebScraper implements IScraping {
	private Relic relic;
	private LinkRelicWithFigureAndDynasty linkRelic;
	
	public Relic getRelic() {
		return relic;
	}

	public RelicScrapeVHHNOneBox(String url) throws IOException {
		this.setUrl(url);
		connect();
		linkRelic = new LinkRelicWithFigureAndDynasty();
	}

	public void scraping() {
		
		String title = this.getDoc().select("header > h1").text();
		String name = title.split(" \\(")[0];
		String location;
		String type;
		String desc = this.getDoc().select(".entry-content").text();
		
		if (title.contains("La Khê")) {
			location = "Hà Đông";
		} else if (title.split(" \\(").length > 1) {
			location = title.split(" \\(")[1].replace(")", "");
		} else {
			location = "Hà Nội";
		}
		if (name.contains("Đình")) {
			type = "Đình";
		} else if (name.contains("Chùa")) {
			type = "Chùa";
		} else if (name.contains("Đền")) {
			type = "Đền";
		} else if (name.contains("Miếu")) {
			type = "Miếu";
		} else {
			type = "Nghè";
		}
		
		linkRelic.genLink(desc);
		LinkedList<Figure> figures = linkRelic.getFigures();
		LinkedList<King> kings = linkRelic.getKings();
		LinkedList<Dynasty> dynastys = linkRelic.getDynastys();
		
		relic = new Relic(name, location, type
				, "Unknown", desc, figures, kings, dynastys);
		System.out.println(name);
		System.out.println(location);
		System.out.println(type);
		System.out.println(desc);

	}

}
