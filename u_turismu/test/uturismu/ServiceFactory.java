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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uturismu.service.AccommodationService;
import uturismu.service.CityService;
import uturismu.service.HolidayPackageService;
import uturismu.service.OvernightStayService;
import uturismu.service.TourOperatorService;
import uturismu.service.usecase.BookerRegistrationService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class ServiceFactory {

	// the application-context object
	private static ApplicationContext context;
	// the application-context path
	private static final String CONTEXT_PATH = "applicationContext.xml";

	static {
		context = new ClassPathXmlApplicationContext(CONTEXT_PATH);
	}

	private ServiceFactory() {
	}

	public static BookerRegistrationService getBookerRegistrationService() {
		return context.getBean(BookerRegistrationService.class);
	}

	public static AccommodationService getAccommodationService() {
		return context.getBean(AccommodationService.class);
	}

	public static HolidayPackageService getHolidayPackageService() {
		return context.getBean(HolidayPackageService.class);
	}

	public static OvernightStayService getOvernightStayService() {
		return context.getBean(OvernightStayService.class);
	}

	public static TourOperatorService getTourOperatorService() {
		return context.getBean(TourOperatorService.class);
	}

	public static CityService getCityService() {
		return context.getBean(CityService.class);
	}

}
