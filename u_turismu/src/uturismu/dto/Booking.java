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
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name="BOOKING")
public class Booking implements Serializable {

	private static final long serialVersionUID = 7295908518751530161L;
	private Long id;
	private Date bookingTimestamp;
	private Booker booker;
	private HolidayPackage holidayPackage;
	private Set<BookingAcceptance> bookingAcceptances;
	

	public Booking() {
		bookingAcceptances=new HashSet<BookingAcceptance>();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="booking_timestamp", nullable=false)
	public Date getBookingTimestamp() {
		return bookingTimestamp;
	}

	@ManyToOne
	@JoinColumn(name="id_booker")
	@ForeignKey(name="FK_BOOKING_BOOKER")
	public Booker getBooker() {
		return booker;
	}

	@ManyToOne
	@JoinColumn(name="id_holiday_package")
	@ForeignKey(name="FK_BOOKING_HOLIDAYPACKAGE")
	public HolidayPackage getHolidayPackage() {
		return holidayPackage;
	}

	
	@OneToMany(mappedBy="booking")
	public Set<BookingAcceptance> getBookingAcceptances(){
		return Collections.unmodifiableSet(bookingAcceptances);
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setBookingTimestamp(Date bookingTimestamp) {
		this.bookingTimestamp = bookingTimestamp;
	}

	public void setBooker(Booker booker) {
		this.booker = booker;
	}

	public void setHolidayPackage(HolidayPackage holidayPackage) {
		this.holidayPackage = holidayPackage;
	}
	
	protected void setBookingAcceptances(Set<BookingAcceptance> bookingAcceptances) {
		this.bookingAcceptances=bookingAcceptances;
	}

	public boolean addBookingAcceptance(BookingAcceptance bookingAcceptance){
		return this.bookingAcceptances.add(bookingAcceptance);
	}
	
	public boolean removeBookingAcceptance(BookingAcceptance bookingAcceptance){
		return this.bookingAcceptances.remove(bookingAcceptance);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((booker == null) ? 0 : booker.hashCode());
		result = prime
				* result
				+ ((bookingTimestamp == null) ? 0 : bookingTimestamp.hashCode());
		result = prime * result
				+ ((holidayPackage == null) ? 0 : holidayPackage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Booking))
			return false;
		Booking other = (Booking) obj;
		if (booker == null) {
			if (other.booker != null)
				return false;
		} else if (!booker.equals(other.booker))
			return false;
		if (bookingTimestamp == null) {
			if (other.bookingTimestamp != null)
				return false;
		} else if (!bookingTimestamp.equals(other.bookingTimestamp))
			return false;
		if (holidayPackage == null) {
			if (other.holidayPackage != null)
				return false;
		} else if (!holidayPackage.equals(other.holidayPackage))
			return false;
		return true;
	}

}
