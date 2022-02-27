package uturismu.bean;

public class BookerBean extends AccountBean {

	private static final long serialVersionUID = 628572028913077644L;
	private String taxCode;
	private String firstName;
	private String lastName;

	public BookerBean() {
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
