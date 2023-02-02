package webcrawler.dynasty;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import objects.dynasty.Dynasty;
import objects.figure.King;

public class DynastyScrapeFull {
	private DynastyScrapeName crawlNames;
	private DynastyScrapeWikiFounder crawlFounder;
	private DynastyScrapeWikiKings firstKings;
	private LinkedList<Dynasty> dynastys;
	private DynastyScrapeNKSKings remainedKings;

	public DynastyScrapeFull() {
		dynastys = new LinkedList<Dynasty>();

		firstKings = new DynastyScrapeWikiKings();
		firstKings.scraping();

		crawlNames = new DynastyScrapeName();
		crawlNames.scraping();

		crawlFounder = new DynastyScrapeWikiFounder();
		crawlFounder.scraping();

		remainedKings = new DynastyScrapeNKSKings();
		remainedKings.scraping();
		
		LinkedList<String> dynastyNames = crawlNames.getDynasty_names();

		for (String name : dynastyNames) {
			// System.out.println(name);
			Dynasty d = new Dynasty(name);
			dynastys.add(d);
		}

		for (Dynasty d_1 : firstKings.getDynastys()) {
			for (Dynasty d_2 : dynastys) {
				if (d_1.getName().equals(d_2.getName())) {
					// System.out.println("* " + d_1.getName());
					d_2.setKings(d_1.getKings());
				}
			}
		}
		
		for (Dynasty d_1 : remainedKings.getDynastys()) {
			for (Dynasty d_2 : dynastys) {
				if (d_1.getName().equals(d_2.getName())) {
					// System.out.println("* " + d_1.getName());
					d_2.setKings(d_1.getKings());
				}
			}
		}
		

		for (Dynasty d_1 : crawlFounder.getDynastys()) {
			for (Dynasty d_2 : dynastys) {
				if (d_1.getName().equals(d_2.getName())) {
					d_2.setFounder(d_1.getFounder());
					System.out.println("* " + d_2.getName() + "-" + d_2.getFounder());
				}
			}
		}

		for (Dynasty d_1 : dynastys) {
			if (d_1.getFounder() == null) {
				d_1.setFounder(new King("Unknown"));
			}
			System.out.println("* " + d_1.getName() + "-" + d_1.getFounder());
		}

		for (Dynasty d : dynastys) {
			if (d.getKings() == null) {
				DynastyScrapeWikiWandKings d_w = new DynastyScrapeWikiWandKings(d.getName());
				d_w.scraping();

				System.out.println("** " + d.getName());
				System.out.println(d_w.getKings().size());
				
				d.setKings(d_w.getKings());
			}

			DynastyScrapeWikiYear d_y = new DynastyScrapeWikiYear(d.getName());
			d_y.scraping();

			d.setStartYear(d_y.getBeginYear());
			d.setEndYear(d_y.getEndYear());
			// System.out.println(d.getName() + " " + d.getStartYear() + " " +
			// d.getEndYear());

			DynastyScrapeWikiCapital d_c = new DynastyScrapeWikiCapital(d.getName());
			d_c.scraping();

			d.setCapital(d_c.getCapital());

			// System.out.println(d.getName() + " " + d.getCapital());

		}

	}

	public void toJson() throws JsonIOException, IOException {
		String filePath = "D:\\dynasty.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(dynastys, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws JsonIOException, IOException {
		DynastyScrapeFull f = new DynastyScrapeFull();
		f.toJson();
	}
}
