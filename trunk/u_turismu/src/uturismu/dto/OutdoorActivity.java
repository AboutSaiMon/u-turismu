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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import uturismu.dto.util.ActivityType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name="OUTDOOR_ACTIVITY")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "activity_type" }))
public class OutdoorActivity extends Service {

	private static final long serialVersionUID = -5512888246149101861L;
	private String name;
	private ActivityType activityType;
	private City city;
	
	public OutdoorActivity() {
	}

	public String getName() {
		return name;
	}

	@Column(nullable = false, name = "activity_type")
	public ActivityType getActivityType() {
		return activityType;
	}

	@ManyToOne
	@JoinColumn(name="id_city")
	@ForeignKey(name="FK_OUTDOORACTIVITY_CITY")
	public City getCity() {
		return city;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutdoorActivity other = (OutdoorActivity) obj;
		if (activityType != other.activityType)
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}