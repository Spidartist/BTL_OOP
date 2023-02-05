package webcrawler.history_figures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import webcrawler.combine.ICombineData;
import webcrawler.tojson.IWriteJson;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ScrapeFigureMain implements IWriteJson, ICombineData {
	private LinkedList<Figure> list = new LinkedList<Figure>();

	public static void main(String[] args) {
		// ArrayList<String> links = new ArrayList<String>();
		ScrapeFigureMain figure = new ScrapeFigureMain();
		figure.combine();
		try {
			figure.writeJSon();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String replaceTrieuDai(String original) {
		String trieuDai = "";
		switch (original) {
			case "Bắc thuộc lần 1": {
				trieuDai = trieuDai.concat("Nhà Triệu");
				break;
			}
			case "Trưng Nữ Vương": {
				trieuDai = trieuDai.concat("Hai Bà Trưng");
				break;
			}
			case "Nhà Tiền Lý, Triệu": {
				trieuDai = trieuDai.concat("Nhà Tiền Lý");
				break;
			}
			case "Hậu Trần": {
				trieuDai = trieuDai.concat("Nhà Hậu Trần");
				break;
			}
			case "Trịnh - Nguyễn": {
				trieuDai = trieuDai.concat("Nhà Hậu Lê");
				break;
			}
			case "Triều Lê Sơ": {
				trieuDai = trieuDai.concat("Nhà Lê sơ");
				break;
			}
			case "Nam - Bắc Triều": {
				trieuDai = trieuDai.concat("Nhà Mạc");
				break;
			}
			case "Nhà Nguyễn độc lập": {
				trieuDai = trieuDai.concat("Nhà Nguyễn");
				break;
			}
			case "Pháp đô hộ": {
				trieuDai = trieuDai.concat("Đế quốc Việt Nam");
				break;
			}
			case "Nước Việt Nam mới": {
				trieuDai = trieuDai.concat("Việt Nam Dân chủ Cộng hòa");
				break;
			}
			case "Dựng nước": {
				trieuDai = trieuDai.concat("Hồng Bàng thị");
				break;
			}
			default: {
				trieuDai = trieuDai.concat(original);
			}
		}
		return trieuDai;
	}

	@Override
	public void writeJSon() throws JsonIOException, IOException {
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\data\\figureUpdate.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() {
		int pageIndex = 1;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";

		while (pageIndex <= 800) {
			String url = urlFirstHalf + Integer.toString(pageIndex);
			VanSu vanSu = new VanSu(url);
			vanSu.scraping();
			list.add(vanSu.getFigure());
			pageIndex += 1;
		}

		System.out.println("num of mem: " + list.size());
		for (Figure figure : list) {
			ArrayList<Dynasty> dynastyList = figure.getTrieuDai();
			for (Dynasty dynasty : dynastyList) {
				String name = dynasty.getName();
				dynasty.setName(replaceTrieuDai(name));
			}
		}
	}
}
