package uturismu.bean;

import java.util.Date;

import org.hibernate.validator.constraints.Email;

import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IdType;

public class BookerSignup implements UTurismuBean {

	private static final long serialVersionUID = 4702479286477388263L;
	@Email
	private String email;
	private String password;
	private String taxCode;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthDate;
	private Long birthPlace;

	private String street;
	private String zipCode;
	private Long city;

	private String identificationDocumentNumber;
	private IdType identificationDocumentType;
	private String issuingAuthority;

	public BookerSignup() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public IdType getIdentificationDocumentType() {
		return identificationDocumentType;
	}

	public void setIdentificationDocumentType(IdType identificationDocumentType) {
		this.identificationDocumentType = identificationDocumentType;
	}

	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

}
