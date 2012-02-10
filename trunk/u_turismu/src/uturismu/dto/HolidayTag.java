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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "HOLIDAY_TAG")
public class HolidayTag implements Serializable {

	private static final long serialVersionUID = -7833352862425542082L;
	private Long id;
	private String name;
	private String description;
	private Set<HolidayPackage> holidayPackages;

	public HolidayTag() {
		holidayPackages = new HashSet<HolidayPackage>();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	@Column(nullable = false)
	public String getDescription() {
		return description;
	}

	/*
	 * By default Hibernate will lazy load collections. In other words, it won't
	 * go to the database to retrieve the list of holiday packages until it
	 * absolutely needs to. What that means is the returned object from your dao
	 * layer won't have the holidayPackage list initialized until you try to access it.
	 * 
	 * When you do try to access it, you're no longer within the session, and so
	 * you get the exception.You can explicitly disable lazy fetching on that
	 * list property by setting lazy="false" in the hibernate mapping, which will
	 * make sure the entire property is populated before returning from your dao
	 * layer
	 */
	@ManyToMany(mappedBy = "holidayTags", fetch = FetchType.EAGER)
	public Set<HolidayPackage> getHolidayPackages() {
		return holidayPackages;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		HolidayTag other = (HolidayTag) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
