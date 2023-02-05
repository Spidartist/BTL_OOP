package webcrawler.full;

import java.io.IOException;

import com.google.gson.JsonIOException;

import webcrawler.combine.ICombineData;
import webcrawler.dynasty.DynastyScrapeFull;
import webcrawler.event.ScrapeEvent;
import webcrawler.history_figures.FindKing;
import webcrawler.history_figures.ScrapeFigureUpdate;
import webcrawler.relic.RelicScrapeFull;

public class ScrapeFull implements ICombineData {
	private DynastyScrapeFull dynasty; 
	private RelicScrapeFull relic;
	private FindKing king;
	private ScrapeFigureUpdate figure;
	private ScrapeEvent event;
	
	public ScrapeFull() throws JsonIOException, IOException {
		king = new FindKing();
		relic = new RelicScrapeFull();
		figure = new ScrapeFigureUpdate();
		event = new ScrapeEvent();
		dynasty = new DynastyScrapeFull();		
	}

	@Override
	public void combine() throws IOException {
		king.scraping();
		king.writeJSon();
		
		figure.combine();
		figure.writeJSon();
		
		dynasty.combine();
		dynasty.writeJSon();
		
		relic.combine();
		relic.writeJSon();
		
		event.combine();
		event.writeJSon();
	}
	
	public static void main(String[] args) throws JsonIOException, IOException {
		ScrapeFull full = new ScrapeFull();
		full.combine();
	}

}
