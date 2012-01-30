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

import javax.persistence.Entity;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity
public class HolidayClassification implements Serializable {

	private static final long serialVersionUID = -3330068729315050006L;
	private Long id;
	private HolidayPackage holidayPackage;
	private HolidayTag holidayTag;

	public HolidayClassification() {
	}

	public Long getId() {
		return id;
	}

	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}

	public HolidayTag getHolidayTag() {
		return holidayTag;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}

	public void setHolidayTag(HolidayTag holidayTag) {
		this.holidayTag = holidayTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((holidayPackage == null) ? 0 : holidayPackage.hashCode());
		result = prime * result + ((holidayTag == null) ? 0 : holidayTag.hashCode());
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
		HolidayClassification other = (HolidayClassification) obj;
		if (holidayPackage == null) {
			if (other.holidayPackage != null)
				return false;
		} else if (!holidayPackage.equals(other.holidayPackage))
			return false;
		if (holidayTag == null) {
			if (other.holidayTag != null)
				return false;
		} else if (!holidayTag.equals(other.holidayTag))
			return false;
		return true;
	}

}
