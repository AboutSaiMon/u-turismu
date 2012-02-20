package uturismu.bean;

public class BookerUpdate extends UserSignup {

	private static final long serialVersionUID = 4702479286477388263L;
	private String taxCode;
	private String firstName;
	private String lastName;
	private String gender;
	private Integer birthDay;
	private Integer birthMonth;
	private Integer birthYear;
	private Long birthPlace;

	private String street;
	private String zipCode;
	private Long city;

	private String identificationDocumentNumber;
	private String identificationDocumentType;
	private String issuingAuthority;

	public BookerUpdate() {
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Long getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(Long birthPlace) {
		this.birthPlace = birthPlace;
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

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public String getIdentificationDocumentNumber() {
		return identificationDocumentNumber;
	}

	public void setIdentificationDocumentNumber(String identificationDocumentNumber) {
		this.identificationDocumentNumber = identificationDocumentNumber;
	}

	public String getIdentificationDocumentType() {
		return identificationDocumentType;
	}

	public void setIdentificationDocumentType(String identificationDocumentType) {
		this.identificationDocumentType = identificationDocumentType;
	}

	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

}
