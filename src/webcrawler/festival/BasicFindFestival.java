package webcrawler.festival;

import java.util.ArrayList;

import objects.festival.Festival;
import objects.figure.Figure;
import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;

public abstract class BasicFindFestival extends BasicWebScraper implements IScraping{
	protected ArrayList<Festival> list = new ArrayList<Festival>();

	public ArrayList<Festival> getList() {
		return list;
	}

	public void setList(ArrayList<Festival> list) {
		this.list = list;
	}
	@Override
	public void scraping() {
		
	}
}
