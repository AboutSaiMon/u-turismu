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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uturismu.HashUtil;
import uturismu.dao.AccountDao;
import uturismu.dao.BookerDao;
import uturismu.dao.HolidayPackageDao;
import uturismu.dao.TourOperatorDao;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.exception.InvalidCredentialException;

/**
 * Vedi la documentazione dell'interfaccia {@link UserService}.
 * 
 * @author "LagrecaSpaccarotella" team.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private BookerDao bookerDao;
	@Autowired
	private TourOperatorDao tourOperatorDao;
	@Autowired
	private HolidayPackageDao holidayPackageDao;

	@Override
	public void createUser(Account account, Booker booker) {
		accountDao.save(account);
		bookerDao.save(booker);
	}

	@Override
	public void createUser(Account account, TourOperator tourOperator) {
		accountDao.save(account);
		tourOperatorDao.save(tourOperator);
	}

	@Override
	@Transactional(readOnly = true)
	public Account logIn(String email, String password) throws InvalidCredentialException {
		Account account = accountDao.findByEmail(email);
		if (account == null) {
			throw new InvalidCredentialException();
		}
		String salt = account.getSalt();
		if (!HashUtil.getHash(password, salt).equals(account.getPassword())) {
			throw new InvalidCredentialException();
		}
		return account;
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getHolidayPackages() {
		return holidayPackageDao.findAllPublished();
	}

	@Override
	@Transactional(readOnly = true)
	public Long getHolidayPackagesNumber() {
		return holidayPackageDao.getAllPublishedNumber();
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getHolidayPackagesByTourOperator(Long id) {
		return holidayPackageDao.findAllPublishedByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getHolidayPackagesByTags(Long... tags) {
		return holidayPackageDao.findAllPublishedByTags(tags);
	}

	@Override
	public List<TourOperator> getTourOperators() {
		return tourOperatorDao.findAll();
	}

}