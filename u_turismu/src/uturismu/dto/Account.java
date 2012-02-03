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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import uturismu.dto.enumtype.AccountType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "ACCOUNT")
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
	private Booker booker;

	public Account() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Column(length = 128)
	public String getPassword() {
		return password;
	}

	@Column(length = 16)
	public String getSalt() {
		return salt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "registration_timestamp", nullable = false)
	public Date getRegistrationTimestamp() {
		return registrationTimestamp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_acces_timestamp", nullable = false)
	public Date getLastAccessTimestamp() {
		return lastAccessTimestamp;
	}

	@Type(type = "yes_no")
	public Boolean isActive() {
		return active;
	}

	@Enumerated(EnumType.STRING)
	public AccountType getType() {
		return type;
	}

	@OneToOne(mappedBy = "account")
	public TourOperator getTourOperator() {
		return tourOperator;
	}

	@OneToOne(mappedBy = "account")
	public Booker getBooker() {
		return booker;
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

	public void setBooker(Booker booker) {
		this.booker = booker;
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

}