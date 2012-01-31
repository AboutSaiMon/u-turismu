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
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

/**
 * @author "LagrecaSpaccarotella" team.
 *
 */
public abstract class AbstractGenericDAO<T , ID extends Serializable> implements GenericDAO<T, ID>{
	private Class<T> persistentClass;
	private SessionFactory sessionFactory;
	
	public AbstractGenericDAO(){
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getPersistentClass(){
		return persistentClass;
	}
	
	
//	@Override
	public T findById(Serializable id) {
		return (T) session().load(getPersistentClass(), id);
	}
	
	
	public List<T> findAll() {
		Criteria criteria=session().createCriteria(getPersistentClass());
		return criteria.list();
	}


	public ID save(T entity) {
		//TODO: SE VOGLIAMO USARE IL SAVE OR UPDATE NON RESTITUISCE NULLA 
		return (ID)session().save(entity);
	}

	public void delete(T entity) {
		session().delete(entity);
	}

	public void update(T entity) {
		session().update(entity);
	}

	public void flush() {
		session().flush();
	}

	public void clear() {
		session().clear();
	}	
}
