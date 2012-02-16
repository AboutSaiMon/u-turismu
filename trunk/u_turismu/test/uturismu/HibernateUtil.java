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
package uturismu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import uturismu.dto.Accommodation;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.Booking;
import uturismu.dto.City;
import uturismu.dto.CityTag;
import uturismu.dto.EventTag;
import uturismu.dto.HolidayPackage;
import uturismu.dto.HolidayTag;
import uturismu.dto.OneOffEvent;
import uturismu.dto.OutdoorActivity;
import uturismu.dto.OvernightStay;
import uturismu.dto.POI;
import uturismu.dto.PeriodicEvent;
import uturismu.dto.Reservation;
import uturismu.dto.Service;
import uturismu.dto.Station;
import uturismu.dto.TourOperator;
import uturismu.dto.Transport;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Accommodation.class);
			configuration.addAnnotatedClass(Account.class);
			configuration.addAnnotatedClass(Booker.class);
			configuration.addAnnotatedClass(Booking.class);
			configuration.addAnnotatedClass(City.class);
			configuration.addAnnotatedClass(CityTag.class);
			configuration.addAnnotatedClass(EventTag.class);
			configuration.addAnnotatedClass(HolidayPackage.class);
			configuration.addAnnotatedClass(HolidayTag.class);
			configuration.addAnnotatedClass(OneOffEvent.class);
			configuration.addAnnotatedClass(OutdoorActivity.class);
			configuration.addAnnotatedClass(OvernightStay.class);
			configuration.addAnnotatedClass(PeriodicEvent.class);
			configuration.addAnnotatedClass(POI.class);
			configuration.addAnnotatedClass(Reservation.class);
			configuration.addAnnotatedClass(Service.class);
			configuration.addAnnotatedClass(Station.class);
			configuration.addAnnotatedClass(TourOperator.class);
			configuration.addAnnotatedClass(Transport.class);
			configuration.configure("mysql.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

}
