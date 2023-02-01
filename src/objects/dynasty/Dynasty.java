package objects.dynasty;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import objects.ParseJSON;

public class Dynasty implements ParseJSON {
	private String startYear; // co ve de
	private String endYear; // chua code
	private String name; // xong, day la base
	private ArrayList<String> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	// private ArrayList<String> events;
	private String founder; // done

	public Dynasty() {

	}

	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String capital,
			String founder) {
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = capital;
		this.founder = founder;
	}

	public Dynasty(String name) {
		super();
		this.name = name;
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

	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String location) {
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

	@Override
	public Dynasty parseDataObject(JSONObject data) {
		String startYear = (String) data.get("startYear");
		String endYear = (String) data.get("endYear");
		String name = (String) data.get("name");

		String capital = (String) data.get("capital");
		String founder = (String) data.get("founder");
		ArrayList<String> kings = new ArrayList<String>();
		JSONArray listKing = (JSONArray) data.get("kings");
		for (int i = 0; i < listKing.size(); i++) {
			kings.add((String) listKing.get(i));
		}
		// kings.forEach(elm -> ());
		// Figure newFigure = new Figure(ten, namSinh, namMat, queQuan, tenKhac,
		// namNhapNgu, ghiChu, namDoTrangNguyen);
		Dynasty newDynasty = new Dynasty(startYear, endYear, name, kings, capital,
				founder);
		return newDynasty;
	}

	
}
