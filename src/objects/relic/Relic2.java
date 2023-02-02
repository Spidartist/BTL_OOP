package objects.relic;

public class Relic2 extends AbstractRelic{
	private String location;
	private String desc;
	private String type;
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	public Relic2(String name, String location, String desc, String type) {
		super();
		this.name = name;
		this.location = location;
		this.desc = desc;
		this.type = type;
	}

	public Relic2() {
		// TODO Auto-generated constructor stub
	}

}
