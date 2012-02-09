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

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
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
	public List<HolidayPackage> findAllPublishedByTourOperator(Long id) {
		Criteria criteria = session().createCriteria(HolidayPackage.class);
		Criterion isPublished = Restrictions.eq("status", Status.PUBLISHED);
		Criterion tourOperatorBinded = Restrictions.eq("tourOperator.id", id);
		criteria.add(Restrictions.and(isPublished, tourOperatorBinded));
		return criteria.list();
	}

	@Override
	public List<HolidayPackage> findAllDrafByTourOperator(Long id) {
		Criteria criteria =this.session().createCriteria(HolidayPackage.class);
		Criterion criterionDraft = Restrictions.eq("status", Status.DRAFT);
		Criterion criterionTour = Restrictions.eq("tourOperator.id", id);
		criteria.add(Restrictions.and(criterionDraft, criterionTour));
		return criteria.list();
	}

	@Override
	public List<HolidayPackage> findAllExpiredByTourOperator(Long id) {
		Criteria criteria =this.session().createCriteria(HolidayPackage.class);
		Criterion criterionDraft = Restrictions.eq("status", Status.EXPIRED);
		Criterion criterionTour = Restrictions.eq("tourOperator.id", id);
		criteria.add(Restrictions.and(criterionDraft, criterionTour));
		return criteria.list();
	}

	@Override
	public List<HolidayPackage> findAllByTourOperator(Long id) {
		Criteria criteria=this.session().createCriteria(HolidayPackage.class);
		criteria.add(Restrictions.eq("tourOperator.id", id));
		return criteria.list();
	}

	//TODO: Implementare Modifica HolidayPackage
	
}
