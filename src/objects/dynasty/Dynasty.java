package objects.dynasty;

import java.util.LinkedList;

import objects.figure.King;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import objects.ParseJSON;

public class Dynasty implements ParseJSON {
	private String startYear; // co ve de
	private String endYear; // chua code
	private String name; // xong, day la base
<<<<<<< HEAD
	private ArrayList<String> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	// private ArrayList<String> events;
	private String founder; // done
=======
	private LinkedList<King> kings; // 1 phan, 8 ban ghi
	private String capital; // co ve day la thu do thi hop ly hon
	private King founder; // done
>>>>>>> cfbd4ef8d55749a3d21506ff2bc503dc5f8687c5

	public Dynasty() {

	}

<<<<<<< HEAD
	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String capital,
			String founder) {
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = capital;
		this.founder = founder;
	}

=======
>>>>>>> cfbd4ef8d55749a3d21506ff2bc503dc5f8687c5
	public Dynasty(String name) {
		super();
		this.name = name;
	}

<<<<<<< HEAD
	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings) {
=======
	public Dynasty(String startYear, String endYear, String name, LinkedList<King> kings, String capital,
			King founder) {
>>>>>>> cfbd4ef8d55749a3d21506ff2bc503dc5f8687c5
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
<<<<<<< HEAD
=======
		this.capital = capital;
		this.founder = founder;
>>>>>>> cfbd4ef8d55749a3d21506ff2bc503dc5f8687c5
	}

	public Dynasty(String startYear, String endYear, String name) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
	}

<<<<<<< HEAD
	public Dynasty(String startYear, String endYear, String name, ArrayList<String> kings, String location) {
		super();
		this.startYear = startYear;
		this.endYear = endYear;
		this.name = name;
		this.kings = kings;
		this.capital = location;
	}

	public String getFounder() {
=======
	public King getFounder() {
>>>>>>> cfbd4ef8d55749a3d21506ff2bc503dc5f8687c5
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
