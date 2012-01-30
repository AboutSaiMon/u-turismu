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

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import uturismu.dto.util.StationType;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
@Entity
public class Station implements Serializable {

	private static final long serialVersionUID = 259910721548074249L;
	private Long id;
	private String name;
	private Address address;
	private StationType type;
	//TO CHECK : GESTIONE  delle doppie stazioni e doppi trasporti
	private Set<Transport> departureTransports;
	private Set<Transport> arrivalTransports;
	
	public Station() {
		
		name="";
		address=new Address();
		departureTransports=new HashSet<Transport>();
		arrivalTransports=new HashSet<Transport>();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(nullable=false)
	public String getName() {
		return name;
	}
	
	@Embedded
	public Address getAddress() {
		return address;
	}

	@Column(nullable=false)
	public StationType getType() {
		return type;
	}

	@OneToMany(mappedBy="departureStation")
	public Set<Transport> getDepartureTransports() {
		return Collections.unmodifiableSet(departureTransports);
	}

	@OneToMany(mappedBy="arrivalStation")
	public Set<Transport> getArrivalTransports() {
		return Collections.unmodifiableSet(arrivalTransports);
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setType(StationType type) {
		this.type = type;
	}

	protected void setDepartureTransports(Set<Transport> departureTransports) {
		this.departureTransports = departureTransports;
	}

	protected void setArrivalTransports(Set<Transport> arrivalTransports) {
		this.arrivalTransports = arrivalTransports;
	}

	public boolean addDepartureTransport(Transport transport){
		return this.departureTransports.add(transport);
	}
	public boolean removeDepartureTransport(Transport transport){
		return this.departureTransports.remove(transport);
	}
	
	public boolean addArrivalTransport(Transport transport){
		return this.arrivalTransports.add(transport);
	}
	public boolean removeArrivalTransport(Transport transport){
		return this.arrivalTransports.remove(transport);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
		Station other = (Station) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Station [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
}
