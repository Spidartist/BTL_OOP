package objects.dynasty;

import java.util.ArrayList;
import java.util.LinkedList;

import objects.figure.King;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import objects.ParseJSON;

public class Dynasty implements ParseJSON {
	private String startYear; // co ve de
	private String endYear; // chua code
	private String name; // xong, day la base
	private ArrayList<King> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	private King founder; // done

	public Dynasty() {

	}

	public Dynasty(String name) {
		super();
		this.name = name;
	}

	public Dynasty(String startYear, String endYear, String name, ArrayList<King> kings, String capital,
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

	public ArrayList<King> getKings() {
		return kings;
	}

	public void setKings(ArrayList<King> kings) {
		this.kings = kings;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String location) {
		this.capital = location;
	}

	@Override
	public Dynasty parseDataObject(JSONObject data) {
		String startYear = (String) data.get("startYear");
		String endYear = (String) data.get("endYear");
		String name = (String) data.get("name");

		String capital = (String) data.get("capital");
		JSONObject founderJSon = (JSONObject)data.get("founder");
		King founder = new King((String)founderJSon.get("ten"));
		ArrayList<King> kings = new ArrayList<King>();
		JSONArray listKing = (JSONArray) data.get("kings");
		for (int i = 0; i < listKing.size(); i++) {
			JSONObject king = (JSONObject) listKing.get(i);
			 King newKing = new King((String) king.get("ten"));
			kings.add(newKing);
		}
		// kings.forEach(elm -> ());
		// Figure newFigure = new Figure(ten, namSinh, namMat, queQuan, tenKhac,
		// namNhapNgu, ghiChu, namDoTrangNguyen);
		Dynasty newDynasty = new Dynasty(startYear, endYear, name, kings, capital,
				founder);
		return newDynasty;
	}

}
