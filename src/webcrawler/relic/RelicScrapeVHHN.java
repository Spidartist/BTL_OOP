package webcrawler.relic;

import java.io.IOException;
import java.util.LinkedList;

import objects.relic.Relic;
import webcrawler.combine.ICombineData;

public class RelicScrapeVHHN implements ICombineData{
	
	private LinkedList<Relic> relics;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetFigure = 0;
	
	public RelicScrapeVHHN() throws IOException {
		relics = new LinkedList<Relic>();
	}
	
	
	
	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int getLienKetFigure() {
		return lienKetFigure;
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
			lienKetDynasty += r_one.getLienKetDynasty();
			lienKetKing += r_one.getLienKetKing();
			lienKetFigure += r_one.getLienKetFigure();
			relics.addAll(r_one.getRelics());
		}
		System.out.println(cnt);
		System.out.println(lienKetDynasty);
		System.out.println(lienKetFigure);
		System.out.println(lienKetKing);
	}

}
