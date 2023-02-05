package webcrawler.relic;

import java.io.IOException;
import java.util.LinkedList;

import objects.relic.Relic;
import webcrawler.combine.ICombineData;

public class RelicScrapeVHHN implements ICombineData{
	
	private LinkedList<Relic> relics;
	
	public RelicScrapeVHHN() throws IOException {
		relics = new LinkedList<Relic>();
	}

	public LinkedList<Relic> getRelics() {
		return relics;
	}
	
	public static void main(String[] args) throws IOException {
		RelicScrapeVHHN r = new RelicScrapeVHHN();
		r.combine();
	}

	@Override
	public void combine() throws IOException {
		int cnt = 0;
		for (int i=1;i<=44;i++) {
			RelicScrapeVHHNOnePage r_one = new RelicScrapeVHHNOnePage(i);
			r_one.scraping();
			cnt += r_one.getRelics().size();
			relics.addAll(r_one.getRelics());
		}
		System.out.println(cnt);
	}

}
