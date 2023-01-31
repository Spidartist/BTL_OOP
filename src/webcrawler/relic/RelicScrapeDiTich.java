package webcrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import objects.relic.Relic1;

public class RelicScrapeDiTich{
	LinkedList<Relic1> relics;
	public RelicScrapeDiTich() {
		relics = new LinkedList<Relic1>();
		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
		for (int i = 1865;i<=6139;i++) {
			System.out.println(i);
			String url = baseUrl + Integer.toString(i);
			RelicScrapeDiTichOneBox r = new RelicScrapeDiTichOneBox(url);
			r.scraping();
			Relic1 r1 = new Relic1(r.getName(), r.getAddress(), r.getType(), r.getRank(), r.getPerson());
			relics.add(r1);
		}
	}
	
	public void toJson() throws JsonIOException, IOException {
		String filePath = "D:\\relic1.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(relics, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws JsonIOException, IOException {
		RelicScrapeDiTich rd = new RelicScrapeDiTich();
		rd.toJson();
	}
}
