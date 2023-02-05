package webcrawler.linkdata;

import java.io.IOException;
import java.util.LinkedList;

import application.readdata.ReadData;
import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

public class LinkRelicWithFigureAndDynasty extends LinkData {
	private LinkedList<Figure> figures;
	private LinkedList<King> kings;
	private LinkedList<Dynasty> dynastys;
	private LinkedList<String> added;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetFigure = 0;
	
	public LinkRelicWithFigureAndDynasty() throws IOException {
		listObservablesFigure = new ReadData<Figure>()
				.FromJsonToArray("src/data/figureUpdate.json", Figure.class);

		listObservablesKing = new ReadData<King>().FromJsonToArray("src/data/king.json",
				King.class);
	}
	
	@Override
	public void genLink(String tenNguoiTho) {
		this.figures = new LinkedList<Figure>();
		this.kings = new LinkedList<King>();
		this.dynastys = new LinkedList<Dynasty>();
		this.added = new LinkedList<String>();
		for (King f : listObservablesKing) {
			if (f.getTen() != null) {
				if (f.getTenHuy() == null && f.getThuyHieu() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				} else if (f.getTenHuy() == null && f.getThuyHieu() != null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getThuyHieu().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				} else if (f.getTenHuy() != null && f.getThuyHieu() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getTenHuy().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				} else {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getTenHuy().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getThuyHieu().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				}
			}

		}

		for (Figure f : listObservablesFigure) {
			if (f.getTenKhac() == null) {
				if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())) {
					figures.add(new Figure(f.getTen()));
					for (Dynasty d : f.getTrieuDai()) {
						if (!added.contains(d.getName())) {
							added.add(d.getName());
							dynastys.add(new Dynasty(d.getName()));
						}
					}
				}
			} else if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
					|| tenNguoiTho.toLowerCase().contains(f.getTenKhac().toLowerCase())) {
				figures.add(new Figure(f.getTen()));
				for (Dynasty d : f.getTrieuDai()) {
					if (!added.contains(d.getName())) {
						added.add(d.getName());
						dynastys.add(new Dynasty(d.getName()));
					}
				}
			}

		}
		
		if (figures.size() != 0) {
			lienKetFigure += figures.size();
			System.out.println("Found!!!!!!");
			for (int j=0;j<figures.size();j++) {
				System.out.println(figures.get(j).getTen());
			}
		}
		
		if (kings.size() != 0) {
			lienKetKing += kings.size();
			System.out.println("Found King!!!!!!");
			for (int j=0;j<kings.size();j++) {
				System.out.println(kings.get(j).getTen());
			}
		}
		
		if (dynastys.size() != 0) {
			lienKetDynasty += dynastys.size();
			System.out.println("Found D!!!!!!");
			for (int j=0;j<dynastys.size();j++) {
				System.out.println(dynastys.get(j).getName());
			}
		}
		
	}
	
	

	public void setLienKetKing(int lienKetKing) {
		this.lienKetKing = lienKetKing;
	}

	public void setLienKetDynasty(int lienKetDynasty) {
		this.lienKetDynasty = lienKetDynasty;
	}

	public void setLienKetFigure(int lienKetFigure) {
		this.lienKetFigure = lienKetFigure;
	}

	public int getLienKetKing() {
		return lienKetKing;
	}

	public int getLienKetDynasty() {
		return lienKetDynasty;
	}

	public int getLienKetFigure() {
		return lienKetFigure;
	}

	public LinkedList<Figure> getFigures() {
		return figures;
	}

	public LinkedList<King> getKings() {
		return kings;
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

}
