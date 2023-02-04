package webcrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import objects.relic.Relic;
import webcrawler.combine.CombineData;

public class RelicScrapeFull implements CombineData{
	private LinkedList<Relic> relics;
	public RelicScrapeFull() {
		relics = new LinkedList<Relic>();
	}

	@Override
	public void combine() throws IOException {
		RelicScrapeDiTich r_d = new RelicScrapeDiTich();
		r_d.combine();
		relics.addAll(r_d.getRelics());
		
		RelicScrapeVHHN r_h = new RelicScrapeVHHN();
		r_h.combine();
		relics.addAll(r_h.getRelics());
	}
	
	public void toJson() throws JsonIOException, IOException {
		String filePath = "D:\\relic_new_2.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(relics, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
	
	public static void main(String[] args) throws IOException {
		RelicScrapeFull r_f = new RelicScrapeFull();
		r_f.combine();
		r_f.toJson();
	}

}
