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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import uturismu.dto.util.TransportType;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
@Entity
public class Transport extends Service {

	private static final long serialVersionUID = 5499793625458647910L;
	private Long id;
	private Date departureTimestamp;
	private TransportType type;
	private Station departureStation;
	private Station arrivalStation;

	public Transport() {
		departureTimestamp=new GregorianCalendar().getTime();
		departureStation=new Station();
		arrivalStation=new Station();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDepartureTimestamp() {
		return departureTimestamp;
	}

	@Column(nullable=false)
	public TransportType getType() {
		return type;
	}

	@ManyToOne()
	@JoinColumn(name="id_departure_station")
	@ForeignKey(name="FK_DEPARTURE_TRANSPORT_STATION")
	public Station getDepartureStation() {
		return departureStation;
	}

	@ManyToOne()
	@JoinColumn(name="id_arrival_station")
	@ForeignKey(name="FK_ARRIVAL_TRANSPORT_STATION")
	public Station getArrivalStation() {
		return arrivalStation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDepartureTimestamp(Date departureTimestamp) {
		this.departureTimestamp = departureTimestamp;
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
		result = prime * result
				+ ((arrivalStation == null) ? 0 : arrivalStation.hashCode());
		result = prime
				* result
				+ ((departureStation == null) ? 0 : departureStation.hashCode());
		result = prime
				* result
				+ ((departureTimestamp == null) ? 0 : departureTimestamp
						.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Transport))
			return false;
		Transport other = (Transport) obj;
		if (arrivalStation == null) {
			if (other.arrivalStation != null)
				return false;
		} else if (!arrivalStation.equals(other.arrivalStation))
			return false;
		if (departureStation == null) {
			if (other.departureStation != null)
				return false;
		} else if (!departureStation.equals(other.departureStation))
			return false;
		if (departureTimestamp == null) {
			if (other.departureTimestamp != null)
				return false;
		} else if (!departureTimestamp.equals(other.departureTimestamp))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transport [id=");
		builder.append(id);
		builder.append(", departureTimestamp=");
		builder.append(departureTimestamp);
		builder.append(", type=");
		builder.append(type);
		builder.append(", departureStation=");
		builder.append(departureStation);
		builder.append(", arrivalStation=");
		builder.append(arrivalStation);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}