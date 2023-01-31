package objects.dynasty;

import java.util.ArrayList;
import java.util.LinkedList;

public class Dynasty {
	private String startYear; // co ve de
	private String endYear;   // chua code
	private String name;   // xong, day la base
	private LinkedList<String> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	private LinkedList<String> events;
	private String founder;  // done 
	public Dynasty() {
		
	}

	public Dynasty(String startYear, String endYear, String name, LinkedList<String> kings, String location, String founder) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
		this.founder = founder;
	}
	
	public Dynasty(String name) {
		super();
		this.name = name;
	}
	


	public Dynasty(String startYear, String endYear, String name, LinkedList<String> kings, String location,
			LinkedList<String> events, String founder) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
		this.events = events;
		this.founder = founder;
	}




	public Dynasty(String startYear, String endYear, String name, LinkedList<String> kings) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
	}

	public Dynasty(String startYear, String endYear, String name) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
	}


	public Dynasty(String startYear, String endYear, String name, LinkedList<String> kings, String location,
			LinkedList<String> events) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
		this.events = events;
	}



	public Dynasty(String startYear, String endYear, String name, LinkedList<String> kings, String location) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
	}
	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
	}
	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<String> getKings() {
		return kings;
	}

	public void setKings(LinkedList<String> kings) {
		this.kings = kings;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String location) {
		this.capital = location;
	}

}
