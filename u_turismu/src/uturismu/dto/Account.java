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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private Customer customer;

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

	public Boolean isActive() {
		return active;
	}

	public AccountType getType() {
		return type;
	}

	public TourOperator getTourOperator() {
		return tourOperator;
	}

	public Customer getCustomer() {
		return customer;
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

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}