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

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public abstract class AbstractDao<T extends Serializable> implements GenericDao<T> {

	private Class<T> persistentClass;
	private SessionFactory sessionFactory;

	public AbstractDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		// Risolve a runtime il tipo dei parametri della classe
		// AbstractGenericDAO. In questo caso solo "T".
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		// Recupera la prima (e l'unica) classe parametrizzata
		persistentClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T findById(Long id) {
		return (T) session().get(persistentClass, id);
	}

	@Override
	public List<T> findAll() {
		Criteria criteria = session().createCriteria(persistentClass);
		return (List<T>) criteria.list();
	}

	@Override
	public Long save(T entity) {
		return (Long) session().save(entity);
	}

	@Override
	public void delete(T entity) {
		session().delete(entity);
	}

	@Override
	public void update(T entity) {
		session().update(entity);
	}

	@Override
	public Long rowCount() {
		Criteria criteria = session().createCriteria(persistentClass);
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public void flush() {
		session().flush();
	}

	@Override
	public void clear() {
		session().clear();
	}

}
