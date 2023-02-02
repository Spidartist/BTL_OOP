package webcrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import application.readdata.ReadData;
import javafx.collections.ObservableList;
import objects.figure.Figure;
import objects.figure.HistoricalFigure;
import objects.figure.King;
import objects.relic.Relic;

public class RelicScrapeDiTich {
	LinkedList<Relic> relics;

	public RelicScrapeDiTich() throws IOException {
		ArrayList<String> lst = new ArrayList<String>();
		
		ObservableList<Figure> listObservablesFigure = new ReadData<Figure>()
                .FromJsonToArray("src/data/figureUpdate.json", Figure.class);
		
		ObservableList<King> listObservablesKing = new ReadData<King>()
                .FromJsonToArray("src/data/king.json", King.class);
		
		relics = new LinkedList<Relic>();
		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
		
		int lienKet = 0;
		
		for (int i = 1865; i <= 6139; i++) {
			System.out.println(i);
			String url = baseUrl + Integer.toString(i);
			RelicScrapeDiTichOneBox r = new RelicScrapeDiTichOneBox(url);
			r.scraping();
			LinkedList<HistoricalFigure> h = new LinkedList<HistoricalFigure>();
			
			String tenNguoiTho = r.getPerson();
			
			for (King f: listObservablesKing) {
				if (f.getTen() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())){
						h.add(new Figure(f.getTen()));
					}
				}else if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
						|| tenNguoiTho.toLowerCase().contains(f.getTenKhac().toLowerCase())) {
					h.add(new Figure(f.getTen()));
				}
			}
			
			for (Figure f: listObservablesFigure) {
				if (f.getTenKhac() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())){
						h.add(new Figure(f.getTen()));
					}
				}else if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
						|| tenNguoiTho.toLowerCase().contains(f.getTenKhac().toLowerCase())) {
					h.add(new Figure(f.getTen()));
				}
			}
			System.out.println(r.getPerson());
			if (h.size() != 0) {
				lienKet += h.size();
				System.out.println("Found!!!!!!");
				for (int j=0;j<h.size();j++) {
					System.out.println(h.get(j).getTen());
				}
			}
			Relic r1 = new Relic(r.getName(), r.getAddress(), r.getType(), r.getRank(), h);
			relics.add(r1);
		}
		
		System.out.println(lienKet);
		
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
