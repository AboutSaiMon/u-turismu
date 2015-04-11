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
package uturismu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uturismu.dao.BookingDao;
import uturismu.dao.HolidayPackageDao;
import uturismu.dto.Booker;
import uturismu.dto.Booking;
import uturismu.dto.HolidayPackage;
import uturismu.dto.enumtype.Status;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Service
@Transactional
public class BookerServiceImpl implements BookerService {

	@Autowired
	private BookingDao bookingDao;
	@Autowired
	private HolidayPackageDao holidayPackageDao;

	@Override
	public void book(Booker booker, HolidayPackage holidayPackage) {
		if (!holidayPackage.isSoldOut()) {
			Booking booking = new Booking();
			booking.setBookingTimestamp(new Date());
			booking.setBooker(booker);
			booker.addBooking(booking);
			booking.setHolidayPackage(holidayPackage);
			holidayPackage.addBooking(booking);
			
			bookingDao.save(booking);
			holidayPackage.incrementCounter();
			if (holidayPackage.isSoldOut()) {
				holidayPackage.setStatus(Status.EXPIRED);
			}
			holidayPackageDao.update(holidayPackage);
		}
	}

}