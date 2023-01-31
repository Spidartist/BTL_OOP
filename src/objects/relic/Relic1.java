package objects.relic;


public class Relic1 extends AbstractRelic{
	private String location;
	private String type;
	private String rank;
	private String people;

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

	public String getPeople() {
		return people;
	}

	public Relic1(String name, String location, String type, String rank, String people) {
		super();
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.people = people;
		this.name = name;
	}

	

}
