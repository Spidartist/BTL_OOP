package webcrawler.relic;

import java.io.IOException;
import java.util.LinkedList;

import objects.relic.Relic;

public class RelicScrapeVHHN {
	
	private LinkedList<Relic> relics;
	
	public RelicScrapeVHHN() throws IOException {
		relics = new LinkedList<Relic>();
		int cnt = 0;
		for (int i=1;i<=44;i++) {
			RelicScrapeVHHNOnePage r_one = new RelicScrapeVHHNOnePage(i);
			r_one.scraping();
			cnt += r_one.getRelics().size();
			relics.addAll(r_one.getRelics());
		}
		System.out.println(cnt);
	}

	public LinkedList<Relic> getRelics() {
		return relics;
	}
	
	public static void main(String[] args) throws IOException {
		RelicScrapeVHHN r = new RelicScrapeVHHN();
	}

}
