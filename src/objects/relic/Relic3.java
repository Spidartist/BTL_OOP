package objects.relic;

import java.util.LinkedList;

import objects.dynasty.Dynasty;
import objects.figure.HistoricalFigure;

public class Relic3 extends AbstractRelic{
	private String location;
	private String type;
	private String rank;
	private LinkedList<HistoricalFigure> people;
	private LinkedList<Dynasty> dynastys;
	private String desc;
	private int yearEngage;

	public int getYearEngage() {
		return yearEngage;
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

	public LinkedList<HistoricalFigure> getPeople() {
		return people;
	}

	public void setPeople(LinkedList<HistoricalFigure> people) {
		this.people = people;
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	public void setDynastys(LinkedList<Dynasty> dynastys) {
		this.dynastys = dynastys;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	public Relic3(String name, String location, String type, String rank) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.rank = rank;
	}

	public Relic3(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public Relic3(String name) {
		super();
		this.name = name;
	}

	public Relic3() {
		// TODO Auto-generated constructor stub
	}

}
