package objects.relic;

import java.util.LinkedList;

import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;

public class Relic{
	private String location;
	private String type;
	private String rank;
	private String desc;
	private LinkedList<Figure> figures;
	private LinkedList<King> kings;
	private LinkedList<Dynasty> dynastys;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<Figure> getFigures() {
		return figures;
	}

	public void setFigures(LinkedList<Figure> figures) {
		this.figures = figures;
	}

	public LinkedList<King> getKings() {
		return kings;
	}

	public void setKings(LinkedList<King> kings) {
		this.kings = kings;
	}

	public String getType() {
		return type;
	}

	public String getRank() {
		return rank;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	public void setDynastys(LinkedList<Dynasty> dynastys) {
		this.dynastys = dynastys;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Relic(String name, String location, String type, String rank, String desc, LinkedList<Figure> figures,
			LinkedList<King> kings, LinkedList<Dynasty> dynastys) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.desc = desc;
		this.figures = figures;
		this.kings = kings;
		this.dynastys = dynastys;
	}
}
