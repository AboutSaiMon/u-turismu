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

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import uturismu.dto.enumtype.DateType;
import uturismu.dto.enumtype.Month;
import uturismu.dto.enumtype.OrdinalNumber;
import uturismu.dto.enumtype.WeekDay;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Embeddable
public class EventDate implements Serializable {

	private static final long serialVersionUID = -5167795797935858737L;
	private DateType type;
	private OrdinalNumber ordinalNumber;
	private Integer day;
	private WeekDay weekDay;
	private Month month;

	public EventDate() {
	}

	@Column(name="date_type", nullable = false)
	@Enumerated(EnumType.STRING)
	public DateType getType() {
		return type;
	}

	@Column(name = "ordinal_number")
	@Enumerated(EnumType.STRING)
	public OrdinalNumber getOrdinalNumber() {
		return ordinalNumber;
	}

	public Integer getDay() {
		return day;
	}

	@Column(name = "day_of_week")
	@Enumerated(EnumType.STRING)
	public WeekDay getWeekDay() {
		return weekDay;
	}

	@Enumerated(EnumType.STRING)
	public Month getMonth() {
		return month;
	}

	@Enumerated(EnumType.STRING)
	public void setType(DateType type) {
		this.type = type;
	}

	public void setOrdinalNumber(OrdinalNumber ordinalNumber) {
		this.ordinalNumber = ordinalNumber;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setWeekDay(WeekDay weekDay) {
		this.weekDay = weekDay;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((ordinalNumber == null) ? 0 : ordinalNumber.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((weekDay == null) ? 0 : weekDay.hashCode());
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
		EventDate other = (EventDate) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (month != other.month)
			return false;
		if (ordinalNumber != other.ordinalNumber)
			return false;
		if (type != other.type)
			return false;
		if (weekDay != other.weekDay)
			return false;
		return true;
	}

}
