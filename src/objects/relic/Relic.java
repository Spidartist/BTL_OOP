package objects.relic;

import java.util.ArrayList;

import objects.dynasty.Dynasty;
import objects.figure.HistoricalFigure;

public class Relic extends AbstractRelic{
	private String location;
	private String type;
	private String rank;
	private ArrayList<HistoricalFigure> people;
	private ArrayList<Dynasty> dynastys;
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

	public ArrayList<String> getPeople() {
		return people;
	}

	public void setPeople(ArrayList<String> people) {
		this.people = people;
	}

	public ArrayList<String> getDynastys() {
		return dynastys;
	}

	public void setDynastys(ArrayList<String> dynastys) {
		this.dynastys = dynastys;
	}

	public ArrayList<String> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<String> events) {
		this.events = events;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	public Relic(String name, String location, String type, String rank, ArrayList<String> people) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.people = people;
	}

	public Relic(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public Relic(String name) {
		super();
		this.name = name;
	}

	public Relic() {
		// TODO Auto-generated constructor stub
	}

}
