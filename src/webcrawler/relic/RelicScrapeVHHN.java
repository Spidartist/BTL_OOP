package webcrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

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
		r.toJson();
	}
	
	public void toJson() throws JsonIOException, IOException {
		String filePath = "D:\\relic_test.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(relics, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
