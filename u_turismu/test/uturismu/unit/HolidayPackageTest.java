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
package uturismu.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.springframework.test.context.transaction.BeforeTransaction;

import uturismu.dto.HolidayPackage;
import uturismu.dto.OvernightStay;
import uturismu.dto.TourOperator;
import uturismu.dto.util.ServiceType;
import uturismu.service.HolidayPackageService;
import uturismu.service.OvernightStayService;
import uturismu.service.TourOperatorService;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
public class HolidayPackageTest extends BaseTest {

	private HolidayPackageService dao;
	
	
	@Before
	public void createHolidayPakage(){
		HolidayPackageService dao= context.getBean(HolidayPackageService.class);
		OvernightStayService dao1=context.getBean(OvernightStayService.class);
		TourOperatorService dao2=context.getBean(TourOperatorService.class);
		
		
		
		
		OvernightStay hotel=new OvernightStay();
		hotel.setArrivalDate(new GregorianCalendar().getTime());
		hotel.setLeavingDate(new GregorianCalendar().getTime());
		hotel.setServiceType(ServiceType.FULL_SERVICE);
		hotel.setPrice(35d);
		
		TourOperator top=new TourOperator();
		top.setName("Il Viaggio");
		
		
		HolidayPackage holidayPackage=new HolidayPackage();
		holidayPackage.setName("alpiMe");
		holidayPackage.setTourOperator(top);
		holidayPackage.addService(hotel);
		holidayPackage.setGuestNumber(1);
		
		
		dao2.save(top);
		dao1.save(hotel);
		Long id= dao.save(holidayPackage);
		
		HolidayPackage queried=dao.findById(id);
		
		assertThat(holidayPackage.getId(), is(equalTo(queried.getId())));
		
	}
	
	@Test
	@Ignore("Mi da un problema con il lazy (inizialization exception)")
	public void updateHP(){
		
		HolidayPackageService dao = context.getBean(HolidayPackageService.class);
		
		String descr="una prova di testing unit";
		
		HolidayPackage holidayPackage=dao.findById(1L);
		
		holidayPackage.setDescription(descr);
		dao.save(holidayPackage);
		
		HolidayPackage hp=dao.findById(1L);
		
		assertThat(hp.getDescription(), is(org.hamcrest.Matchers.not(null)));
		
		hp=dao.findById(2L);
		assertThat(hp.getDescription(), is(equalTo(null)));
		
		
		
	}
	
	@Test
	public void deleteHolidayPackage(){
		HolidayPackageService dao=context.getBean(HolidayPackageService.class);
		
		Long id=new Long(1);
		Long w=1l;
		System.out.println(w);
		
		HolidayPackage hp=dao.findById(id);
		assertThat(id, is(equalTo(hp.getId())));
		
		List<HolidayPackage> queryList= dao.findAll();
		
//		assertThat(queryList.size(), is(org.hamcrest.Matchers.not(null)));
		assertThat(queryList.size(), is(1));
		
		
		for (HolidayPackage holidayPackage : queryList) {
			dao.delete(holidayPackage);
		}
		
		queryList=dao.findAll();
		
		assertThat(queryList.size(), is(equalTo(0)));
		
	}
	
}
