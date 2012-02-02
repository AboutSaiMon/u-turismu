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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import uturismu.dto.enumtype.AccommodationType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "ACCOMMODATION")
public class Accommodation implements Serializable {

	private static final long serialVersionUID = 226753614504147949L;
	private Long id;
	private String vatNumber;
	private String name;
	private Address address;
	private AccommodationType type;
	private Set<OvernightStay> overnightsStay;

	public Accommodation() {
		overnightsStay = new HashSet<OvernightStay>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "vat_number", nullable = false, unique = true)
	public String getVatNumber() {
		return vatNumber;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@Embedded
	public Address getAddress() {
		return address;
	}

	@Enumerated(EnumType.STRING)
	public AccommodationType getType() {
		return type;
	}

	@OneToMany(mappedBy = "accommodation")
	public Set<OvernightStay> getOvernightsStay() {
		return Collections.unmodifiableSet(overnightsStay);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setType(AccommodationType type) {
		this.type = type;
	}

	protected void setOvernightsStay(Set<OvernightStay> overnightsStay) {
		this.overnightsStay = overnightsStay;
	}

	public boolean addOvernightStay(OvernightStay overnightStay) {
		return overnightsStay.add(overnightStay);
	}

	public boolean removeOvernightStay(OvernightStay overnightStay) {
		return overnightsStay.remove(overnightStay);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((vatNumber == null) ? 0 : vatNumber.hashCode());
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
		Accommodation other = (Accommodation) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		if (vatNumber == null) {
			if (other.vatNumber != null)
				return false;
		} else if (!vatNumber.equals(other.vatNumber))
			return false;
		return true;
	}

}