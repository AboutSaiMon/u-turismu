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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IDType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = -6323910189513397033L;
	private Long id;
	/* è il codice fiscale */
	private String taxCode;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Date birthDate;
	private City birthPlace;
	private Address livingPlace;
	/* è il codice del documento identificativo */
	private String identificationDocumentNumber;
	/* è la tipologia (patente, passaporto, ecc) */
	private IDType identificationDocumentType;
	private String issuingAuthority;
	private Booker booker;
	private Set<Booking> bookings;

	public Customer() {
		bookings = new HashSet<Booking>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "tax_code", unique = true, length = 16)
	public String getTaxCode() {
		return taxCode;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "last_name", nullable = false)
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
	@JoinColumn(name = "id_birth_place", nullable = false)
	public City getBirthPlace() {
		return birthPlace;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "street", column = @Column(name = "living_place_street")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "living_place_zipcode")),
			@AttributeOverride(name = "city", column = @Column(name = "living_place_city")) })
	public Address getLivingPlace() {
		return livingPlace;
	}

	@Column(name = "identification_document_number", nullable = false)
	public String getIdentificationDocumentNumber() {
		return identificationDocumentNumber;
	}

	@Column(name = "identification_document_type")
	public IDType getIdentificationDocumentType() {
		return identificationDocumentType;
	}

	@Column(name = "issuing_authority", nullable = false)
	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	@OneToOne(mappedBy = "customer")
	public Booker getBooker() {
		return booker;
	}

	@ManyToMany(mappedBy = "customers")
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

	public void setLivingPlace(Address livingPlace) {
		this.livingPlace = livingPlace;
	}

	public void setIdentificationDocumentNumber(String idNumber) {
		this.identificationDocumentNumber = idNumber;
	}

	public void setIdentificationDocumentType(IDType idType) {
		this.identificationDocumentType = idType;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public void setBooker(Booker booker) {
		this.booker = booker;
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
		result = prime * result + ((livingPlace == null) ? 0 : livingPlace.hashCode());
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
		Customer other = (Customer) obj;
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
		if (livingPlace == null) {
			if (other.livingPlace != null)
				return false;
		} else if (!livingPlace.equals(other.livingPlace))
			return false;
		if (taxCode == null) {
			if (other.taxCode != null)
				return false;
		} else if (!taxCode.equals(other.taxCode))
			return false;
		return true;
	}

}
