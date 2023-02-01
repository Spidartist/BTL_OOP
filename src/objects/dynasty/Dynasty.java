package objects.dynasty;

import java.util.LinkedList;

import objects.figure.King;

public class Dynasty {
	private String startYear; // co ve de
	private String endYear; // chua code
	private String name; // xong, day la base
	private LinkedList<King> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	private King founder; // done

	public Dynasty() {

	}

	public Dynasty(String name) {
		super();
		this.name = name;
	}

	public Dynasty(String startYear, String endYear, String name, LinkedList<King> kings, String capital,
			King founder) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = capital;
		this.founder = founder;
	}

	public Dynasty(String startYear, String endYear, String name) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
	}

	public King getFounder() {
		return founder;
	}

	public void setFounder(King founder) {
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

	public LinkedList<King> getKings() {
		return kings;
	}

	public void setKings(LinkedList<King> kings) {
		this.kings = kings;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String location) {
		this.capital = location;
	}

}
