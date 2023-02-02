package objects.relic;

import java.util.LinkedList;

import objects.figure.Figure;
import objects.figure.King;

public class Relic extends AbstractRelic{
	private String location;
	private String type;
	private String rank;
	private LinkedList<Figure> figures;
	private LinkedList<King> kings;
	
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

	public Relic(String location, String type, String rank, LinkedList<Figure> figures, LinkedList<King> kings) {
		super();
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.figures = figures;
		this.kings = kings;
	}

	

	

}
