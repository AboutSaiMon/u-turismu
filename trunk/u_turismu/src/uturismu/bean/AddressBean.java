package uturismu.bean;


public class AddressBean implements UTurismuBean {

	private static final long serialVersionUID = -7978271789748038627L;
	private String street;
	private String zipCode;
	private Long cityId;

	public AddressBean() {
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

}
