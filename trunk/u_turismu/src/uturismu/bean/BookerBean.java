package uturismu.bean;

import java.util.Date;

import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IdType;

public class BookerBean extends AccountBean {

	private static final long serialVersionUID = 628572028913077644L;
	private String taxCode;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthDate;
	private Long birthPlaceId;
	private AddressBean residence;
	private String identificationDocumentNumber;
	private IdType identificationDocumentType;
	private String issuingAuthority;

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

	public Long getBirthPlaceId() {
		return birthPlaceId;
	}

	public void setBirthPlaceId(Long birthPlaceId) {
		this.birthPlaceId = birthPlaceId;
	}

	public AddressBean getResidence() {
		return residence;
	}

	public void setResidence(AddressBean residence) {
		this.residence = residence;
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
