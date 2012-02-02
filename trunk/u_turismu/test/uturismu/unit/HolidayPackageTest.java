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
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import uturismu.ServiceFactory;
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
public class HolidayPackageTest {

	private static HolidayPackageService holidayPackageService;
	private static OvernightStayService overnightStayService;
	private static TourOperatorService tourOperatorService;

	@BeforeClass
	public static void init() {
		holidayPackageService = ServiceFactory.getHolidayPackageService();
		overnightStayService = ServiceFactory.getOvernightStayService();
		tourOperatorService = ServiceFactory.getTourOperatorService();
	}

	/*
	 * Il problema sta in questo metodo. Non puoi salvare un Holiday Package quì
	 * perché i metodi annotati con @Before vengono richiamati tante volte quanti
	 * sono i metodi annotati con @Test. Siccome c'è un vincolo di unicità sulla
	 * coppia (name, id_tour_operator), la seconda volta che viene eseguito
	 * questo codice, verrà lanciata un'eccezione del tipo
	 * "DataIntegrityViolationException".
	 */
	@Before
	public void createHolidayPakage() {
		OvernightStay overnightStay = new OvernightStay();
		overnightStay.setArrivalDate(new GregorianCalendar().getTime());
		overnightStay.setLeavingDate(new GregorianCalendar().getTime());
		overnightStay.setServiceType(ServiceType.FULL_SERVICE);
		overnightStay.setPrice(35d);

		TourOperator top = new TourOperator();
		top.setName("Il Viaggio");

		HolidayPackage holidayPackage = new HolidayPackage();
		holidayPackage.setName("alpiMe");
		holidayPackage.setTourOperator(top);
		holidayPackage.addService(overnightStay);
		holidayPackage.setGuestNumber(1);

		tourOperatorService.save(top);
		overnightStayService.save(overnightStay);
		Long id = holidayPackageService.save(holidayPackage);
		HolidayPackage queried = holidayPackageService.findById(id);

		assertThat(holidayPackage.getId(), is(equalTo(queried.getId())));
	}

	/*
	 * Non hai la garanzia che lo stato del DB sia come ti aspetti. I metodi di
	 * test vengono eseguiti con un ordine al di fuori del controllo dello
	 * sviluppatore. Non è detto che venga eseguito prima il test di creazione,
	 * poi quello di update e poi quello di delete.
	 */
	@Test
	@Ignore("Mi da un problema con il lazy (inizialization exception)")
	public void updateHP() {
		String descr = "una prova di testing unit";
		HolidayPackage holidayPackage = holidayPackageService.findById(1L);

		holidayPackage.setDescription(descr);
		holidayPackageService.save(holidayPackage);

		HolidayPackage hp = holidayPackageService.findById(1L);

		assertThat(hp.getDescription(), is(notNullValue()));

		hp = holidayPackageService.findById(2L);
		assertThat(hp.getDescription(), is(nullValue()));

	}

	/*
	 * Non hai la garanzia che lo stato del DB sia come ti aspetti. I metodi di
	 * test vengono eseguiti con un ordine al di fuori del controllo dello
	 * sviluppatore. Non è detto che venga eseguito prima il test di creazione,
	 * poi quello di update e poi quello di delete.
	 */
	@Test
	public void deleteHolidayPackage() {
		Long id = new Long(1);
		Long w = 1l;
		System.out.println(w);

		HolidayPackage hp = holidayPackageService.findById(id);
		assertThat(id, is(equalTo(hp.getId())));

		List<HolidayPackage> queryList = holidayPackageService.findAll();
		// TODO: ALTERNATIVA
		// Integer rowCount = holidayPackageService.rowCount();

		// assertThat(queryList.size(), is(org.hamcrest.Matchers.not(null)));
		assertThat(queryList.size(), is(1));
		// TODO: ALTERNATIVA
		// assertThat(rowCount, is(equalTo(1)));

		for (HolidayPackage holidayPackage : queryList) {
			holidayPackageService.delete(holidayPackage);
		}

		queryList = holidayPackageService.findAll();

		assertThat(queryList.size(), is(equalTo(0)));

	}

}
