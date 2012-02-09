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

import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.exception.InvalidCredentialException;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public interface UserManagementService {

	public void createAccount(Account account, Booker booker);

	public void createAccount(Account account, TourOperator tourOperator);

	public Account login(String email, String password) throws InvalidCredentialException;
	
	public List<HolidayPackage> getHolidayPackages();
	
	public List<HolidayPackage> getHolidayPackagesByTourOperator(Long id);
	
	public List<HolidayPackage> getHolidayPackagesByTags(Long ...tags);
	
	public List<HolidayPackage> getHolidayPackagesByTourOperatorAndTags(Long id, Long ...tags);
	
	public List<TourOperator> getTourOperators();

}