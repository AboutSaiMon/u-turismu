package uturismu.bean;

public class CityBean implements UTurismuBean {

	private static final long serialVersionUID = -8661502959028368665L;
	private Long id;
	private String name;
	
	public CityBean() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
