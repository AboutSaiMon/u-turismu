package uturismu.bean;

public class HolidayPackageBean implements UTurismuBean {

	private static final long serialVersionUID = 3596030132035301414L;
	private Long id;
	private String name;
	private String description;
	private Double price;

	public HolidayPackageBean() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}