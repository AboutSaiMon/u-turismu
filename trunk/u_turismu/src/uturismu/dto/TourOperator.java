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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "TOUR_OPERATOR")
public class TourOperator implements Serializable {

	private static final long serialVersionUID = 4132492228184160094L;
	private Long id;
	private String vatNumber;
	private String name;
	private String holderName;
	private Address headOffice;
	private Account account;
	private Set<HolidayPackage> holidayPackages;

	public TourOperator() {
		holidayPackages = new HashSet<HolidayPackage>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "vat_number", unique = true, length = 11)
	public String getVatNumber() {
		return vatNumber;
	}

	public String getName() {
		return name;
	}

	@Column(name = "holder_name")
	public String getHolderName() {
		return holderName;
	}

	@Embedded
	@Column(name = "head_office")
	public Address getHeadOffice() {
		return headOffice;
	}

	@OneToOne
	@JoinColumn(name = "id_account")
	@ForeignKey(name = "FK_TOUROPERATOR_ACCOUNT")
	public Account getAccount() {
		return account;
	}

	@OneToMany(mappedBy = "tourOperator")
	@Cascade({ CascadeType.SAVE_UPDATE })
	protected Set<HolidayPackage> getHolidayPackages() {
		return holidayPackages;
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

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public void setHeadOffice(Address headOffice) {
		this.headOffice = headOffice;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	protected void setHolidayPackages(Set<HolidayPackage> holidayPackages) {
		this.holidayPackages = holidayPackages;
	}

	public boolean addHolidayPackage(HolidayPackage holidayPackage) {
		holidayPackage.setTourOperator(this);
		return holidayPackages.add(holidayPackage);
	}

	public boolean removeHolidayPackage(HolidayPackage holidayPackage) {
		holidayPackage.setTourOperator(null);
		return holidayPackages.remove(holidayPackage);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((headOffice == null) ? 0 : headOffice.hashCode());
		result = prime * result + ((holderName == null) ? 0 : holderName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		TourOperator other = (TourOperator) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (headOffice == null) {
			if (other.headOffice != null)
				return false;
		} else if (!headOffice.equals(other.headOffice))
			return false;
		if (holderName == null) {
			if (other.holderName != null)
				return false;
		} else if (!holderName.equals(other.holderName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (vatNumber == null) {
			if (other.vatNumber != null)
				return false;
		} else if (!vatNumber.equals(other.vatNumber))
			return false;
		return true;
	}

}