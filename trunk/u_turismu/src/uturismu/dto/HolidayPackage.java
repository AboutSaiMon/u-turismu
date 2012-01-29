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
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "tourOperator" }))
public class HolidayPackage implements Serializable {

	private static final long serialVersionUID = -138153679026481915L;
	private Long id;
	private String name;
	private String description;
	private Integer guestNumber;
	private TourOperator tourOperator;
	private Set<Booking> bookings;
	private Set<Service> services;

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@ManyToOne()
	@JoinColumn(name = "id_tour_operator")
	@ForeignKey(name = "FK_HOLIDAYPACKAGE_TOUROPERATOR")
	public TourOperator getTourOperator() {
		return tourOperator;
	}

	@OneToMany(mappedBy = "holidayPackage")
	public Set<Booking> getBookings() {
		return Collections.unmodifiableSet(bookings);
	}

	@ManyToMany()
	@JoinTable(name = "CATALOG", joinColumns = @JoinColumn(name = "id_holiday_package"), inverseJoinColumns = @JoinColumn(name = "id_service"))
	@ForeignKey(name = "FK_CATALOG_HOLIDAYPACKAGE", inverseName = "FK_CATALOG_SERVICE")
	public Set<Service> getServices() {
		return Collections.unmodifiableSet(services);
	}

	@Column(nullable=false)
	public Integer getGuestNumber() {
		return guestNumber;
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

	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}
	
	public void setTourOperator(TourOperator tourOperator) {
		this.tourOperator = tourOperator;
	}

	protected void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	protected void setServices(Set<Service> services) {
		this.services = services;
	}

	public boolean addBooking(Booking booking) {
		return bookings.add(booking);
	}

	public boolean addService(Service service) {
		return services.add(service);
	}

	public boolean removeBooking(Booking booking) {
		return bookings.remove(booking);
	}

	public boolean removeService(Service service) {
		return services.remove(service);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((services == null) ? 0 : services.hashCode());
		result = prime * result + ((tourOperator == null) ? 0 : tourOperator.hashCode());
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
		HolidayPackage other = (HolidayPackage) obj;
		if (bookings == null) {
			if (other.bookings != null)
				return false;
		} else if (!bookings.equals(other.bookings))
			return false;
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
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		if (tourOperator == null) {
			if (other.tourOperator != null)
				return false;
		} else if (!tourOperator.equals(other.tourOperator))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HolidayPackage [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", tourOperator=");
		builder.append(tourOperator);
		builder.append(", bookings=");
		builder.append(bookings);
		builder.append(", services=");
		builder.append(services);
		builder.append("]");
		return builder.toString();
	}

}
