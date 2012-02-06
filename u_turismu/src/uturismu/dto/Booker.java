/*
 * This file is part of "U Turismu" project. 
 * 
 * U Turismu is an enterprise application in support of calabrian tour operators.
 * This system aims to promote tourist services provided by the operators
 * and to develop and improve tourism in Calabria.
 *
 * Copyright (C) 2012 "LagrecaSpaccarotella" team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uturismu.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IdType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "BOOKER")
public class Booker implements Serializable {

	private static final long serialVersionUID = -4043855751210103797L;
	private Long id;
	private String taxCode;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthDate;
	private City birthPlace;
	private Address residence;
	private String identificationDocumentNumber;
	private IdType identificationDocumentType;
	private String issuingAuthority;
	private Account account;
	private Set<Booking> bookings;

	public Booker() {
		bookings = new HashSet<Booking>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "tax_code", length = 16, unique = true, nullable = false)
	public String getTaxCode() {
		return taxCode;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	public Date getBirthDate() {
		return birthDate;
	}

	@ManyToOne
	@JoinColumn(name = "id_birth_place")
	@ForeignKey(name = "FK_BOOKER_CITY")
	public City getBirthPlace() {
		return birthPlace;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street", column = @Column(name = "residence_street")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "residence_zipcode")),
			@AttributeOverride(name = "city", column = @Column(name = "residence_city")) })
	public Address getResidence() {
		return residence;
	}

	@Column(name = "identification_document_number")
	public String getIdentificationDocumentNumber() {
		return identificationDocumentNumber;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "identification_document_type")
	public IdType getIdentificationDocumentType() {
		return identificationDocumentType;
	}

	@Column(name = "issuing_authority")
	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	@OneToOne
	@JoinColumn(name = "id_account")
	public Account getAccount() {
		return account;
	}

	@OneToMany(mappedBy = "booker")
	public Set<Booking> getBookings() {
		return Collections.unmodifiableSet(bookings);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setBirthPlace(City birthPlace) {
		this.birthPlace = birthPlace;
	}

	public void setResidence(Address residence) {
		this.residence = residence;
	}

	public void setIdentificationDocumentNumber(String identificationDocumentNumber) {
		this.identificationDocumentNumber = identificationDocumentNumber;
	}

	public void setIdentificationDocumentType(IdType identificationDocumentType) {
		this.identificationDocumentType = identificationDocumentType;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	protected void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public boolean addBooking(Booking booking) {
		return bookings.add(booking);
	}

	public boolean removeBooking(Booking booking) {
		return bookings.remove(booking);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((birthPlace == null) ? 0 : birthPlace.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime
				* result
				+ ((identificationDocumentNumber == null) ? 0 : identificationDocumentNumber.hashCode());
		result = prime * result
				+ ((identificationDocumentType == null) ? 0 : identificationDocumentType.hashCode());
		result = prime * result + ((issuingAuthority == null) ? 0 : issuingAuthority.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((residence == null) ? 0 : residence.hashCode());
		result = prime * result + ((taxCode == null) ? 0 : taxCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booker other = (Booker) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (birthPlace == null) {
			if (other.birthPlace != null)
				return false;
		} else if (!birthPlace.equals(other.birthPlace))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (identificationDocumentNumber == null) {
			if (other.identificationDocumentNumber != null)
				return false;
		} else if (!identificationDocumentNumber.equals(other.identificationDocumentNumber))
			return false;
		if (identificationDocumentType != other.identificationDocumentType)
			return false;
		if (issuingAuthority == null) {
			if (other.issuingAuthority != null)
				return false;
		} else if (!issuingAuthority.equals(other.issuingAuthority))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (residence == null) {
			if (other.residence != null)
				return false;
		} else if (!residence.equals(other.residence))
			return false;
		if (taxCode == null) {
			if (other.taxCode != null)
				return false;
		} else if (!taxCode.equals(other.taxCode))
			return false;
		return true;
	}

}
