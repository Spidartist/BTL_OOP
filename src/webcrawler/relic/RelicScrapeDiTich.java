package webcrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;
import objects.relic.Relic;
import webcrawler.combine.CombineData;
import webcrawler.linkdata.LinkRelicWithFigureAndDynasty;

public class RelicScrapeDiTich implements CombineData{
	LinkedList<Relic> relics;

	public RelicScrapeDiTich() throws IOException {
		relics = new LinkedList<Relic>();
	}

	public void toJson() throws JsonIOException, IOException {
		String filePath = "D:\\relic_new.json";
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
		rd.combine();
		rd.toJson();
	}
	
	
	
	public LinkedList<Relic> getRelics() {
		return relics;
	}

	@Override
	public void combine() throws IOException {
		LinkRelicWithFigureAndDynasty linkRelic = new LinkRelicWithFigureAndDynasty();

		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
		int lienKet = 0;
		for (int i = 1865; i <= 6139; i++) {
			
			String url = baseUrl + Integer.toString(i);
			RelicScrapeDiTichOnePage r = new RelicScrapeDiTichOnePage(url);
			r.scraping();
			if (r.getName().strip() != "") {
				System.out.println(i);
				System.out.println(r.getName());
				System.out.println(r.getAddress());
				System.out.println(r.getPerson());
				String tenNguoiTho = r.getPerson();
				
				linkRelic.genLink(tenNguoiTho);
				LinkedList<Figure> figures = linkRelic.getFigures();
				LinkedList<King> kings = linkRelic.getKings();
				LinkedList<Dynasty> dynastys = linkRelic.getDynastys();
				
				lienKet += linkRelic.getLienKet();
				
				Relic r1 = new Relic(r.getName(), r.getAddress(), r.getType()
						, r.getRank(), tenNguoiTho, figures, kings, dynastys);
				relics.add(r1);
			}
			
		}
		
		System.out.println(lienKet);
	}
}
