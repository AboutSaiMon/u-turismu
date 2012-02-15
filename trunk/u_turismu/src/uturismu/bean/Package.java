package uturismu.bean;

public class Package {
	
	private Long id;
	private String Name;
	private String description;
	private Double prize;
	public Long getId() {
		return id;
	}
	public String getName() {
		return Name;
	}
	public String getDescription() {
		return description;
	}
	public Double getPrize() {
		return prize;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrize(Double prize) {
		this.prize = prize;
	}
	
	
}
