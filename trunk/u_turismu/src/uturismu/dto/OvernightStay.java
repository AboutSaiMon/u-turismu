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

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uturismu.dto.util.ServiceType;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
@Entity
public class OvernightStay extends Service {

	private static final long serialVersionUID = 1066966536889642801L;
	private Long id;
	private Date arrivalDate;
	private Date leavingDate;
	private ServiceType serviceType;
	private Accommodation accomodation;
	

	public OvernightStay() {
		arrivalDate=new GregorianCalendar().getTime();
		leavingDate=new GregorianCalendar().getTime();
		accomodation=new Accommodation();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	@Column(name="arrival_date",nullable=false)
	@Temporal(TemporalType.DATE)
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	@Column(name="leaving_date",nullable=false )
	@Temporal(TemporalType.DATE)
	public Date getLeavingDate() {
		return leavingDate;
	}

	@Column(name="service_type", nullable=false)
	public ServiceType getServiceType() {
		return serviceType;
	}
	
	@Column(nullable=false)
	public Accommodation getAccomodation() {
		return accomodation;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}
	
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	public void setAccomodation(Accommodation accomodation) {
		this.accomodation = accomodation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((accomodation == null) ? 0 : accomodation.hashCode());
		result = prime * result
				+ ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result
				+ ((leavingDate == null) ? 0 : leavingDate.hashCode());
		result = prime * result
				+ ((serviceType == null) ? 0 : serviceType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof OvernightStay))
			return false;
		OvernightStay other = (OvernightStay) obj;
		if (accomodation == null) {
			if (other.accomodation != null)
				return false;
		} else if (!accomodation.equals(other.accomodation))
			return false;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (leavingDate == null) {
			if (other.leavingDate != null)
				return false;
		} else if (!leavingDate.equals(other.leavingDate))
			return false;
		if (serviceType != other.serviceType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OvernightStay [id=");
		builder.append(id);
		builder.append(", arrivalDate=");
		builder.append(arrivalDate);
		builder.append(", leavingDate=");
		builder.append(leavingDate);
		builder.append(", serviceType=");
		builder.append(serviceType);
		builder.append(", accomodation=");
		builder.append(accomodation);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	
	
}
