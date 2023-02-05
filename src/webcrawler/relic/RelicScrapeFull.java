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
import webcrawler.tojson.IWriteJson;

public class RelicScrapeFull implements ICombineData, IWriteJson {
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
		System.out.println(r_h.getLienKetDynasty());
		System.out.println(r_h.getLienKetFigure());
		System.out.println(r_h.getLienKetKing());
	}

	public void writeJSon() throws JsonIOException, IOException {
		String filePath = "C:\\Users\\ASUS\\eclipse-workspace\\quan_crawl\\src\\data\\relic.json";
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
		r_f.writeJSon();
	}

}
