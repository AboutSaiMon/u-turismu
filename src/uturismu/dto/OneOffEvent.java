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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import uturismu.dto.enumtype.EventType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "ONE_OFF_EVENT")
public class OneOffEvent implements Serializable {

	private static final long serialVersionUID = -5759172658129579393L;
	private Long id;
	private String name;
	private String description;
	private EventType type;
	private Date date;
	// espressa in giorni
	private Integer duration;
	private City city;
	private Set<EventTag> eventTags;

	public OneOffEvent() {
		eventTags = new HashSet<EventTag>();
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Enumerated(EnumType.STRING)
	public EventType getType() {
		return type;
	}

	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public Integer getDuration() {
		return duration;
	}

	@ManyToOne
	@JoinColumn(name = "id_city")
	@ForeignKey(name = "FK_ONEOFFEVENT_CITY")
	public City getCity() {
		return city;
	}

	@ManyToMany
	@JoinTable(name = "ONEOFF_EVENT_CLASSIFICATION", joinColumns = @JoinColumn(name = "id_oneoff_event"), inverseJoinColumns = @JoinColumn(name = "id_event_tag"))
	@ForeignKey(name = "FK_ONEOFFEVENTCLASSIFICATION_ONEOFFEVENT", inverseName = "FK_ONEOFFEVENTCLASSIFICATION_EVENTTAG")
	protected Set<EventTag> getEventTags() {
		return eventTags;
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

	public void setType(EventType type) {
		this.type = type;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setCity(City city) {
		this.city = city;
	}

	protected void setEventTags(Set<EventTag> eventTags) {
		this.eventTags = eventTags;
	}

	public boolean addEventTag(EventTag eventTag) {
		return this.eventTags.add(eventTag);
	}

	public boolean removeEventTag(EventTag eventTag) {
		return this.eventTags.remove(eventTag);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((eventTags == null) ? 0 : eventTags.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		OneOffEvent other = (OneOffEvent) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (eventTags == null) {
			if (other.eventTags != null)
				return false;
		} else if (!eventTags.equals(other.eventTags))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
