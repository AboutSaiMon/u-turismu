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
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
@Entity

public abstract class Service implements Serializable {

	private static final long serialVersionUID = 3469323821178357386L;
	private Long id;
	private Double price;
	private Set<HolidayPackage> holidayPackages;
	
	public Long getId() {
		return id;
	}
	public Double getPrice() {
		return price;
	}
	
	@ManyToMany(mappedBy="services")
	public Set<HolidayPackage> getHolidayPackages() {
		return holidayPackages;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setHolidayPackages(Set<HolidayPackage> holidayPackages) {
		this.holidayPackages = holidayPackages;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((holidayPackages == null) ? 0 : holidayPackages.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Service))
			return false;
		Service other = (Service) obj;
		if (holidayPackages == null) {
			if (other.holidayPackages != null)
				return false;
		} else if (!holidayPackages.equals(other.holidayPackages))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Service [id=");
		builder.append(id);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

	
	
}
