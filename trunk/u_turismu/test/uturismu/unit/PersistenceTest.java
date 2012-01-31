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

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uturismu.HibernateUtil;
import uturismu.dto.HolidayPackage;
import uturismu.service.HolidayPackageService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class PersistenceTest {

	@Test
	public void createSchemaWithHibernate() {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		transaction.commit();
		session.close();
	}

	@Test
	public void persistWithSpring() {
		String contextPath = "uturismu/applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(contextPath);
		HolidayPackageService service = (HolidayPackageService) context
				.getBean("holidayPackageService");

		HolidayPackage p = new HolidayPackage();
		p.setName("Vacanze nel mediterraneo");
		p.setGuestNumber(2);
		p.setDescription("Viaggio per due con crociera");

		service.save(p);
	}

}
