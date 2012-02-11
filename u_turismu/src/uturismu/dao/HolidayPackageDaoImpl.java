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
package uturismu.dao;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import uturismu.dto.HolidayPackage;
import uturismu.dto.enumtype.Status;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
@Repository
public class HolidayPackageDaoImpl extends AbstractDao<HolidayPackage> implements HolidayPackageDao {

	@Autowired
	public HolidayPackageDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<HolidayPackage> findAllPublished() {
		Criteria criteria = session().createCriteria(HolidayPackage.class);
		criteria.add(Restrictions.eq("status", Status.PUBLISHED));
		return criteria.list();
	}

	@Override
	public Long getAllPublishedNumber() {
		Criteria criteria = session().createCriteria(HolidayPackage.class);
		criteria.add(Restrictions.eq("status", Status.PUBLISHED));
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
		/*
		 * String queryString = "select count(*) from HolidayPackage p"; Query
		 * query = session().createQuery(queryString); //
		 * query.setParameter("status", Status.PUBLISHED); return (Integer)
		 * query.uniqueResult();
		 */
	}

	@Override
	public List<HolidayPackage> findAllPublishedByTourOperator(Long id) {
		Criteria criteria = session().createCriteria(HolidayPackage.class);
		criteria.add(Restrictions.eq("status", Status.PUBLISHED));
		criteria.add(Restrictions.eq("tourOperator.id", id));
		return criteria.list();
	}

	@Override
	public List<HolidayPackage> findAllPublishedByTags(Long... tags) {
		StringBuilder queryBuilder = new StringBuilder(
				"select distinct package.id from HOLIDAY_PACKAGE package");
		for (int i = 1; i <= tags.length; i++) {
			queryBuilder.append(", HOLIDAY_CLASSIFICATION c").append(i);
		}
		queryBuilder
				.append(" where package.status = 'PUBLISHED' and package.id = c1.id_holiday_package");
		for (int i = 1; i < tags.length; i++) {
			queryBuilder.append(" and c").append(i).append(".id_holiday_package = c").append(i + 1)
					.append(".id_holiday_package");
		}
		for (int i = 1; i <= tags.length; i++) {
			queryBuilder.append(" and c").append(i).append(".id_holiday_tag = ?");
		}
		SQLQuery query = session().createSQLQuery(queryBuilder.toString());
		for (int i = 0; i < tags.length; i++) {
			query.setParameter(i, tags[i]);
		}
		List<BigInteger> holidayPackages = query.list();
		Criteria criteria = session().createCriteria(HolidayPackage.class);
		Disjunction disjunction = Restrictions.disjunction();
		for (BigInteger id : holidayPackages) {
			disjunction.add(Restrictions.idEq(id.longValue()));
		}
		criteria.add(disjunction);
		return criteria.list();
	}

	/*
	 * @Override public List<HolidayPackage> findAllPublishedByTags(Long... tags)
	 * { // carica l'oggetto persistente che rappresenta il tag HolidayTag tag =
	 * (HolidayTag) session().load(HolidayTag.class, tags[0]); // acquisisce gli
	 * holiday package a cui il tag è associato Set<HolidayPackage> packages =
	 * tag.getHolidayPackages(); // setta il primo risultato parziale
	 * List<HolidayPackage> result = new ArrayList<HolidayPackage>(packages); //
	 * dichiara la variabile temporanea List<HolidayPackage> temp = null; // per
	 * ogni tag passato in input (tranne il primo) for (int i = 1; i <
	 * tags.length; i++) { // carica l'oggetto persistente che rappresenta il tag
	 * tag = (HolidayTag) session().load(HolidayTag.class, tags[i]); //
	 * acquisisce gli holiday package a cui il tag è associato packages =
	 * tag.getHolidayPackages(); // inizializza la variabile temporanea temp =
	 * new ArrayList<HolidayPackage>(); // per ogni holiday package for
	 * (HolidayPackage holidayPackage : packages) { // se è presente nel
	 * risultato parziale, vuol dire che il tag annota // entrambi gli holiday
	 * package. Allora va aggiunto alla variabile // temporanea, ma solo se è
	 * pubblicato. if (result.contains(holidayPackage) &&
	 * holidayPackage.getStatus().equals(Status.PUBLISHED)) {
	 * temp.add(holidayPackage); } } result = temp; } return result; }
	 */

	@Override
	public List<HolidayPackage> findAllDraftByTourOperator(Long id) {
		Criteria criteria = this.session().createCriteria(HolidayPackage.class);
		Criterion criterionDraft = Restrictions.eq("status", Status.DRAFT);
		Criterion criterionTour = Restrictions.eq("tourOperator.id", id);
		criteria.add(Restrictions.and(criterionDraft, criterionTour));
		return criteria.list();
	}

	@Override
	public List<HolidayPackage> findAllExpiredByTourOperator(Long id) {
		Criteria criteria = this.session().createCriteria(HolidayPackage.class);
		Criterion criterionDraft = Restrictions.eq("status", Status.EXPIRED);
		Criterion criterionTour = Restrictions.eq("tourOperator.id", id);
		criteria.add(Restrictions.and(criterionDraft, criterionTour));
		return criteria.list();
	}

	@Override
	public List<HolidayPackage> findAllByTourOperator(Long id) {
		Criteria criteria = this.session().createCriteria(HolidayPackage.class);
		criteria.add(Restrictions.eq("tourOperator.id", id));
		return criteria.list();
	}

	// TODO: Implementare Modifica HolidayPackage

}
