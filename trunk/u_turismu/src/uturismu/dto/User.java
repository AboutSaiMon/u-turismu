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
import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import uturismu.dto.util.Gender;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = -4043855751210103797L;
	private Long id;
	private Account account;
	private Set<Booking> bookings;
	private Customer customer;
	
	public User() {
		account=new Account();
		bookings=new HashSet<Booking>();
		customer=new Customer();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@OneToOne(mappedBy="user")
	public Account getAccount() {
		return account;
	}

	@OneToMany(mappedBy="user")
	public Set<Booking> getBookings() {
		return Collections.unmodifiableSet(bookings);
	}

	@OneToOne(mappedBy="user")
	public Customer getCustomer() {
		return customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	protected void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public boolean addBooking(Booking booking){
		return this.bookings.add(booking);
	}
	public boolean removeBooking(Booking booking){
		return this.bookings.remove(booking);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
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
		User other = (User) obj;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", account=");
		builder.append(account);
		builder.append(", customer=");
		builder.append(customer);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
