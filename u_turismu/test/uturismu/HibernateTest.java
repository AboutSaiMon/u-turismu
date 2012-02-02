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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import uturismu.dto.Accommodation;
import uturismu.dto.enumtype.AccommodationType;

/**
 * Questo test bypassa Spring ed effettua dei test sul database via Hibernate.
 * 
 * @author "LagrecaSpaccarotella" team.
 */
public class HibernateTest {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();

		Accommodation a = new Accommodation();
		a.setVatNumber("11");
		a.setName("name1");
		a.setType(AccommodationType.HOTEL);
		session.save(a);

		a = new Accommodation();
		a.setVatNumber("11");
		a.setName("name1");
		a.setType(AccommodationType.HOTEL);
		session.save(a);

		transaction.commit();
		session.close();
	}

}
