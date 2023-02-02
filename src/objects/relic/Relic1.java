package objects.relic;

import java.util.LinkedList;

import objects.figure.HistoricalFigure;

public class Relic1 extends AbstractRelic{
	private String location;
	private String type;
	private String rank;
	private LinkedList<HistoricalFigure> people;
	
	public LinkedList<HistoricalFigure> getPeople() {
		return people;
	}

	public void setPeople(LinkedList<HistoricalFigure> realPeople) {
		this.people = realPeople;
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

	public Relic1(String name, String location, String type, String rank, LinkedList<HistoricalFigure> people) {
		super();
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.people = people;
		this.name = name;
	}

	

}
