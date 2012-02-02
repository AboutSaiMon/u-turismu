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

import uturismu.dto.enumtype.TransportType;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Entity(name = "TRANSPORT")
public class Transport extends Service {

	private static final long serialVersionUID = 5499793625458647910L;
	private String companyName;
	private Date departureTimeAndDate;
	private Date arrivalTimeAndDate;
	private TransportType type;
	private Station departureStation;
	private Station arrivalStation;

	public Transport() {
	}

	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "departure_timestamp")
	public Date getDepartureTimeAndDate() {
		return departureTimeAndDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrival_timestamp")
	public Date getArrivalTimeAndDate() {
		return arrivalTimeAndDate;
	}

	@Enumerated(EnumType.STRING)
	public TransportType getType() {
		return type;
	}

	@ManyToOne
	@JoinColumn(name = "id_departure_station", nullable = false)
	@ForeignKey(name = "FK_TRANSPORT_DEPARTURESTATION")
	public Station getDepartureStation() {
		return departureStation;
	}

	@ManyToOne
	@JoinColumn(name = "id_arrival_station", nullable = false)
	@ForeignKey(name = "FK_TRANSPORT_ARRIVALSTATION")
	public Station getArrivalStation() {
		return arrivalStation;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setDepartureTimeAndDate(Date departureTimeAndDate) {
		this.departureTimeAndDate = departureTimeAndDate;
	}

	public void setArrivalTimeAndDate(Date arrivalTimeAndDate) {
		this.arrivalTimeAndDate = arrivalTimeAndDate;
	}

	public void setType(TransportType type) {
		this.type = type;
	}

	public void setDepartureStation(Station departureStation) {
		this.departureStation = departureStation;
	}

	public void setArrivalStation(Station arrivalStation) {
		this.arrivalStation = arrivalStation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((arrivalStation == null) ? 0 : arrivalStation.hashCode());
		result = prime * result + ((arrivalTimeAndDate == null) ? 0 : arrivalTimeAndDate.hashCode());
		result = prime * result + ((departureStation == null) ? 0 : departureStation.hashCode());
		result = prime * result
				+ ((departureTimeAndDate == null) ? 0 : departureTimeAndDate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transport other = (Transport) obj;
		if (arrivalStation == null) {
			if (other.arrivalStation != null)
				return false;
		} else if (!arrivalStation.equals(other.arrivalStation))
			return false;
		if (arrivalTimeAndDate == null) {
			if (other.arrivalTimeAndDate != null)
				return false;
		} else if (!arrivalTimeAndDate.equals(other.arrivalTimeAndDate))
			return false;
		if (departureStation == null) {
			if (other.departureStation != null)
				return false;
		} else if (!departureStation.equals(other.departureStation))
			return false;
		if (departureTimeAndDate == null) {
			if (other.departureTimeAndDate != null)
				return false;
		} else if (!departureTimeAndDate.equals(other.departureTimeAndDate))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}