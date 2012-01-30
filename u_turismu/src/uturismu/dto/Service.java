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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name="SERVICE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Service implements Serializable {

	private static final long serialVersionUID = 3469323821178357386L;
	private Long id;
	private Double price;
	private String description;
	private Set<HolidayPackage> holidayPackages;

	public Service() {
		holidayPackages = new HashSet<HolidayPackage>();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	@ManyToMany(mappedBy="services")
	public Set<HolidayPackage> getHolidayPackages() {
		return Collections.unmodifiableSet(holidayPackages);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	protected void setHolidayPackages(Set<HolidayPackage> holidayPackages) {
		this.holidayPackages = holidayPackages;
	}

	public boolean addHolidayPackage(HolidayPackage holidayPackage) {
		return holidayPackages.add(holidayPackage);
	}

	public boolean removeHolidayPackage(HolidayPackage holidayPackage) {
		return holidayPackages.remove(holidayPackage);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

}