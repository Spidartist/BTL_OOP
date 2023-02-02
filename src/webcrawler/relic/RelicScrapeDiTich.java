package webcrawler.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import application.readdata.ReadData;
import javafx.collections.ObservableList;
import objects.figure.Figure;
import objects.figure.King;
import objects.relic.Relic;

public class RelicScrapeDiTich {
	LinkedList<Relic> relics;

	public RelicScrapeDiTich() throws IOException {
		
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
			
			LinkedList<Figure> figures = new LinkedList<Figure>();
			LinkedList<King> kings = new LinkedList<King>();
			
			String tenNguoiTho = r.getPerson();
			
			for (King f: listObservablesKing) {
				if (f.getTen() != null) {
					if (f.getTenHuy() == null && f.getThuyHieu() == null) {
						if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())){
							kings.add(new King(f.getTen()));
						}
					}else if (f.getTenHuy() == null && f.getThuyHieu() != null) {
						if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
								|| tenNguoiTho.toLowerCase().contains(f.getThuyHieu().toLowerCase())) {
							kings.add(new King(f.getTen()));
						}
					}else if (f.getTenHuy() != null && f.getThuyHieu() == null) {
						if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
								|| tenNguoiTho.toLowerCase().contains(f.getTenHuy().toLowerCase())) {
							kings.add(new King(f.getTen()));
						}
					}else {
						if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
								|| tenNguoiTho.toLowerCase().contains(f.getTenHuy().toLowerCase())
								|| tenNguoiTho.toLowerCase().contains(f.getThuyHieu().toLowerCase())) {
							kings.add(new King(f.getTen()));
						}
					}
				}
				
				
			}
			
			for (Figure f: listObservablesFigure) {
				if (f.getTenKhac() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())){
						figures.add(new Figure(f.getTen()));
					}
				}else if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
						|| tenNguoiTho.toLowerCase().contains(f.getTenKhac().toLowerCase())) {
					figures.add(new Figure(f.getTen()));
				}
			}

			if (figures.size() != 0) {
				lienKet += figures.size();
				System.out.println("Found!!!!!!");
				for (int j=0;j<figures.size();j++) {
					System.out.println(figures.get(j).getTen());
				}
			}
			
			if (kings.size() != 0) {
				lienKet += kings.size();
				System.out.println("Found King!!!!!!");
				for (int j=0;j<kings.size();j++) {
					System.out.println(kings.get(j).getTen());
				}
			}
			
			Relic r1 = new Relic(r.getName(), r.getAddress(), r.getType(), r.getRank(), figures, kings);
			relics.add(r1);
		}
		
		System.out.println(lienKet);
		
	}

	public void toJson() throws JsonIOException, IOException {
		String filePath = "D:\\relic.json";
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
