package objects.relic;

import java.util.ArrayList;

public class Relic {
	private String name;
	private String location;
	private String type;
	private String rank;
	private ArrayList<String> people;
	private ArrayList<String> dynastys;
	private ArrayList<String> events;
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
