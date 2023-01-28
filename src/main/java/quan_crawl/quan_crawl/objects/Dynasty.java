package quan_crawl.quan_crawl.objects;

import java.util.ArrayList;

public class Dynasty {
	private String startYear; // co ve de
	private String endYear;   // chua code
	private String name;   // xong, day la base
	private ArrayList<String> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	private ArrayList<String> events;
	private String founder;  // done 
	public Dynasty() {
		
	}

	public String getFounder() {
		return founder;
	}




	public void setFounder(String founder) {
		this.founder = founder;
	}




	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String location, String founder) {
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



	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String location,
			ArrayList<String> events, String founder) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
		this.events = events;
		this.founder = founder;
	}




	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings) {
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


	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String location,
			ArrayList<String> events) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
		this.events = events;
	}



	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String location) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
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

	public ArrayList<String> getKings() {
		return kings;
	}

	public void setKings(ArrayList<String> kings) {
		this.kings = kings;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String location) {
		this.capital = location;
	}

}
