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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import uturismu.dto.enumtype.ServiceType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "OVERNIGHT_STAY")
public class OvernightStay extends Service {

	private static final long serialVersionUID = 1066966536889642801L;
	private Date arrivalDate;
	private Date leavingDate;
	private ServiceType serviceType;
	private Accommodation accommodation;

	public OvernightStay() {
	}

	@Column(name = "arrival_date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getArrivalDate() {
		return arrivalDate;
	}

	@Column(name = "leaving_date", nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getLeavingDate() {
		return leavingDate;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "service_type", nullable = false)
	public ServiceType getServiceType() {
		return serviceType;
	}

	@ManyToOne
	@JoinColumn(name = "id_accomodation")
	@ForeignKey(name = "FK_OVERNIGHTSTAY_ACCOMODATION")
	public Accommodation getAccommodation() {
		return accommodation;
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

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accommodation == null) ? 0 : accommodation.hashCode());
		result = prime * result + ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result + ((leavingDate == null) ? 0 : leavingDate.hashCode());
		result = prime * result + ((serviceType == null) ? 0 : serviceType.hashCode());
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
		if (!super.equals(obj))
			return false;
		OvernightStay other = (OvernightStay) obj;
		if (accommodation == null) {
			if (other.accommodation != null)
				return false;
		} else if (!accommodation.equals(other.accommodation))
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

}
