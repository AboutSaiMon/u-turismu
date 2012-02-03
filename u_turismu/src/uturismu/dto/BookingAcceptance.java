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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "BOOKING_ACCEPTANCE")
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"id_booking", "id_customer"}))
public class BookingAcceptance implements Serializable {

	private static final long serialVersionUID = -3505616914661848863L;
	private Long id;
	private Booking booking;
	private Customer customer;
	private Date acceptanceDate;

	public BookingAcceptance() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name = "id_booking")
	@ForeignKey(name = "FK_BOOKINGACCEPTANCE_BOOKING")
	public Booking getBooking() {
		return booking;
	}

	@ManyToOne
	@JoinColumn(name = "id_customer")
	@ForeignKey(name = "FK_BOOKINGACCEPTANCE_CUSTOMER")
	public Customer getCustomer() {
		return customer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "acceptance_date")
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceptanceDate == null) ? 0 : acceptanceDate.hashCode());
		result = prime * result + ((booking == null) ? 0 : booking.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		BookingAcceptance other = (BookingAcceptance) obj;
		if (acceptanceDate == null) {
			if (other.acceptanceDate != null)
				return false;
		} else if (!acceptanceDate.equals(other.acceptanceDate))
			return false;
		if (booking == null) {
			if (other.booking != null)
				return false;
		} else if (!booking.equals(other.booking))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

}
