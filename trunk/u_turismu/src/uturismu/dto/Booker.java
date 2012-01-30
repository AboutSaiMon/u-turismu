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

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity
public class Booker implements Serializable {

	private static final long serialVersionUID = -4043855751210103797L;
	private Long id;
	private Account account;
	private Customer customer;
	private Set<Booking> bookings;

	public Booker() {
		bookings = new HashSet<Booking>();
	}

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Set<Booking> getBookings() {
		return Collections.unmodifiableSet(bookings);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	protected void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public boolean addBooking(Booking booking) {
		return bookings.add(booking);
	}
	
	public boolean removeBooking(Booking booking) {
		return bookings.remove(booking);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
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
		Booker other = (Booker) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}

}
