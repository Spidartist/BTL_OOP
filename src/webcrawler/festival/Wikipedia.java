package webcrawler.festival;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import webcrawler.parent.BasicWebScraper;
import webcrawler.parent.IScraping;
import objects.festival.Festival;
import objects.figure.Figure;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Wikipedia extends BasicFindFestival {
	
	public Wikipedia() {
		String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
		setUrl(url);
		connect();
	}


	@Override
	public void scraping() {
		Element mainContent = this.doc.getElementsByTag("main").first();
		Element div = mainContent.select("div.mw-parser-output").first();
		Elements lists = div.getElementsByTag("ul");
		Element listLehoi = null;
		for (Element list : lists) {
			Elements li = list.getElementsByTag("li");
			if (li.size() > 30) {
				listLehoi = list;
				break;
			}
		}
		Elements festivals = listLehoi.getElementsByTag("li");
		for (Element fes : festivals) {
			String data = fes.text();
			int splitPoint = data.indexOf(":");
			String diaDiem = data.substring(0, splitPoint);
			if (diaDiem.equals("An Giang") || diaDiem.equals("Bắc Ninh") || diaDiem.equals("Tuyên Quang")
					|| diaDiem.equals("Đà Nẵng")) {
				continue;
			} else {
				String festivalNames = data.substring(splitPoint + 1);
				festivalNames = festivalNames.replace(";", ",");
				festivalNames = modify(diaDiem, festivalNames);
				String[] names = festivalNames.split(",");
				for (String name : names) {
					System.out.println(name);
					String tenLeHoi = "";
					String que = "";
					String thoiGian = "";
					if (name.contains("(")) {
						int open = name.indexOf("(");
						int close = name.indexOf(")");
						tenLeHoi = tenLeHoi.concat(name.substring(0, open));
						que = que.concat(name.substring(open + 1, close));
						que = que.concat(",");
						que = que.concat(diaDiem);
						thoiGian = thoiGian.concat(name.substring(close + 1));
					} else if (name.contains("tháng")) {
						int start = name.indexOf("tháng");
						tenLeHoi = tenLeHoi.concat(name.substring(0, start - 1));
						que = que.concat(diaDiem);
						thoiGian = thoiGian.concat(name.substring(start));
					}

					else {
						char[] ch = name.toCharArray();
						int index = 0;

						for (char c : ch) {
							if (Character.isDigit(c)) {
								break;
							} else {
								tenLeHoi = tenLeHoi.concat(Character.toString(c));
								index++;
							}
						}
						for (int i = index; i < ch.length; i++) {
							thoiGian = thoiGian.concat(Character.toString(ch[i]));
						}
						que = que.concat(diaDiem);
					}

					// System.out.println("Ten Le Hoi: "+tenLeHoi);
					// System.out.println("Thoi gian: "+thoiGian);
					// System.out.println("Dia Diem: "+que);
					Festival festival = new Festival(tenLeHoi, thoiGian, diaDiem);
					Figure figure = new Figure("");
					festival.setFigure(figure);
					festival.setNoiDung("");
					list.add(festival);
				} // end for 2
			}

		} // end for 1
	}

	public ArrayList<Festival> getList() {
		return this.list;
	}

	private String modify(String diaDiem, String festivalNames) {
		String names = festivalNames;
		switch (diaDiem) {
			case "Bắc Giang": {
				int index = names.indexOf(", thành");
				if (index != -1) {
					names = names.replace(", thành", " thành");
				}
				index = names.indexOf(", Việt");
				if (index != -1) {
					names = names.replace(", Việt", " Việt");
				}
				index = names.indexOf(", Sli");
				if (index != -1) {
					names = names.replace("Shoong hao, Sli, Lượn ", "Shoong hao Sli Lượn ");
				}
				break;
			}

			case "Bắc Ninh": {
				int index = names.indexOf(", thành");
				if (index != -1) {
					names = names.replace(", thành", " thành");
				}
				index = names.indexOf(", Từ");
				if (index != -1) {
					names = names.replace(", Từ", " Từ");
				}
				break;
			}

			case "Bình Định": {
				int index = names.indexOf(", Nguyễn");
				if (index != -1) {
					names = names.replace("Trung, Nguyễn Nhạc, Nguyễn Lữ", "Trung Nguyễn Nhạc Nguyễn Lữ");
				}
				index = names.indexOf("3");
				if (index != -1) {
					names = names.replace("3", "ba");
				}
				break;
			}
			case "Cao Bằng": {
				int index = names.indexOf(", Hòa");
				if (index != -1) {
					names = names.replace(", Hòa", " Hòa");
				}
				index = names.indexOf(", Hạ");
				if (index != -1) {
					names = names.replace(", Hạ", " Hạ");
				}
				break;
			}

			case "Cần Thơ": {
				int index = names.indexOf(", 13");
				if (index != -1) {
					names = names.replace("12, 13, 14", "12 13 14");
				}
				break;
			}
			case "Điện Biên": {
				int index = names.indexOf("(khai");
				if (index != -1) {
					names = names.replace("(khai hội)", "khai hội");
				}
				break;
			}
			case "Hà Giang": {
				names = names.replace("(", " ");
				names = names.replace(")", " ");
				break;
			}
			case "Hà Nam": {
				int index = names.indexOf(", huyện");
				if (index != -1) {
					names = names.replace(", huyện", " huyện");
				}
				index = names.indexOf("ngày 18");
				if (index != -1) {
					names = names.replace("ngày 18 – 25 tháng Sáu", "tháng Sáu ngày 18 – 25");
				}
				break;
			}

			case "Hà Nội": {
				int index = names.indexOf(", Hoài");
				if (index != -1) {
					names = names.replace(", Hoài", " Hoài");
				}
				index = names.indexOf(", Gia");
				if (index != -1) {
					names = names.replace(", Gia", " Gia");
				}
				index = names.indexOf(", Long");
				if (index != -1) {
					names = names.replace(", Long", " Long");
				}
				break;
			}
			case "Hưng Yên": {
				int index = names.indexOf(", thành");
				if (index != -1) {
					names = names.replace(", thành", " thành");
				}
				index = names.indexOf("(khai");
				if (index != -1) {
					names = names.replace("(khai hội)", "khai hội");
				}
				index = names.indexOf("(thờ");
				if (index != -1) {
					names = names.replace("(thờ Phạm Ngũ Lão)", "thờ Phạm Ngũ Lão");
				}
				break;
			}
			case "Lào Cai": {
				int index = names.indexOf(":");
				if (index != -1) {
					names = names.replace(":", "");
				}
				break;
			}

			case "Lạng Sơn": {
				int index = names.indexOf("và");
				if (index != -1) {
					names = names.replace("và", ",");
				}
				break;
			}

			case "Lâm Đồng": {
				names = names.replace("(", " ");
				names = names.replace(")", " ");
				names = names.replace(",", "");
				int index = names.indexOf("khoảng");
				if (index != -1) {
					names = names.replace("khoảng", " ");
				}
				break;
			}

			case "Nghệ An": {
				int index = names.indexOf("20, 21/1");
				if (index != -1) {
					names = names.replace("20, 21/1", "20-21/1");
				}
				index = names.indexOf("15 tháng");
				if (index != -1) {
					names = names.replace("15 tháng 3 và 10 tháng 10 âm lịch", "15/3 và 10/10 âm lịch");
				}
				break;
			}

			case "Ninh Bình": {
				int index = names.indexOf(", Hoa");
				if (index != -1) {
					names = names.replace("Ninh Hải, Hoa Lư", "Ninh Hải Hoa Lư");
				}
				break;
			}
			case "Phú Yên": {
				int index = names.indexOf(", xã");
				if (index != -1) {
					names = names.replace(", xã An Cư, huyện Tuy An", " xã An Cư huyện Tuy An");
				}
				index = names.indexOf("Hinh,");
				if (index != -1) {
					names = names.replace(", Sơn Hòa, Đồng Xuân", " Sơn Hòa Đồng Xuân");
				}
				break;
			}

			case "Quảng Ninh": {
				int index = names.indexOf(", 3");
				if (index != -1) {
					names = names.replace("từ 4/1 âm lịch, 3 tháng", "3 tháng từ 4/1 âm lịch");
				}
				break;
			}
			case "Sơn La": {
				int index = names.indexOf(", Kháng");
				if (index != -1) {
					names = names.replace(", Kháng", "và Kháng");
				}
				break;
			}
			case "Tây Ninh": {
				names = names.replace("(", " ");
				names = names.replace(")", " ");
				break;
			}
			case "Thái Bình": {
				int index = names.indexOf("(khai");
				if (index != -1) {
					names = names.replace("(khai hội)", "khai hội");
				}
				index = names.indexOf(", Vũ");
				if (index != -1) {
					names = names.replace(", Vũ", " Vũ");
				}
				break;
			}

			case "Thái Nguyên": {
				int index = names.indexOf(", Phú");
				if (index != -1) {
					names = names.replace(", Phú", " Phú");
				}
				break;
			}
			case "TPHCM": {
				int index = names.indexOf("al");
				if (index != -1) {
					names = names.replace("al", " âm lịch");
				}
				break;
			}
			case "Trà Vinh": {
				int index = names.indexOf("giữa");
				if (index != -1) {
					names = names.replace("giữa", "");
				}
				index = names.indexOf("cuối");
				if (index != -1) {
					names = names.replace("cuối", "");
				}
			}
			case "Vĩnh Long": {
				names = names.replace("(hạ điền)", "");
				names = names.replace("(thượng điền)", "");
				break;
			}
		}
		return names;
	}

	public static void main(String[] args) {
		Wikipedia obj = new Wikipedia();
		obj.scraping();
		String filePath = "D:\\webCrawler\\webcrawler\\src\\webcrawler\\jsonFiles\\festival.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<Festival> festivals = obj.getList();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(festivals, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
