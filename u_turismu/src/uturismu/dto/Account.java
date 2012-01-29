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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import uturismu.dto.util.AccountType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity
public class Account implements Serializable {

	private static final long serialVersionUID = 1902321524953220029L;
	private Long id;
	private String email;
	private String password;
	private String salt;
	private Date registrationTimestamp;
	private Date lastAccessTimestamp;
	private Boolean active;
	private AccountType type;
	private TourOperator tourOperator;
	private User user;

	public Account() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_timestamp")
	public Date getRegistrationTimestamp() {
		return registrationTimestamp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_access_timestamp")
	public Date getLastAccessTimestamp() {
		return lastAccessTimestamp;
	}

	@Type(type = "yes_no")
	public Boolean isActive() {
		return active;
	}

	@Column(nullable = false)
	public AccountType getType() {
		return type;
	}

	@OneToOne(mappedBy = "account")
	public TourOperator getTourOperator() {
		return tourOperator;
	}

	@OneToOne(mappedBy = "customer")
	public User getCustomer() {
		return user;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setRegistrationTimestamp(Date registrationTimestamp) {
		this.registrationTimestamp = registrationTimestamp;
	}

	public void setLastAccessTimestamp(Date lastAccessTimestamp) {
		this.lastAccessTimestamp = lastAccessTimestamp;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void setTourOperator(TourOperator tourOperator) {
		this.tourOperator = tourOperator;
	}

	public void setCustomer(User customer) {
		this.user = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((lastAccessTimestamp == null) ? 0 : lastAccessTimestamp.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((registrationTimestamp == null) ? 0 : registrationTimestamp.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
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
		Account other = (Account) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (lastAccessTimestamp == null) {
			if (other.lastAccessTimestamp != null)
				return false;
		} else if (!lastAccessTimestamp.equals(other.lastAccessTimestamp))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registrationTimestamp == null) {
			if (other.registrationTimestamp != null)
				return false;
		} else if (!registrationTimestamp.equals(other.registrationTimestamp))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", salt=");
		builder.append(salt);
		builder.append(", registrationTimestamp=");
		builder.append(registrationTimestamp);
		builder.append(", lastAccessTimestamp=");
		builder.append(lastAccessTimestamp);
		builder.append(", active=");
		builder.append(active);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}