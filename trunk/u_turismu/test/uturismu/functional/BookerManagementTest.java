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
package uturismu.functional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import uturismu.HashUtil;
import uturismu.ServiceFactory;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.Gender;
import uturismu.exception.InvalidCredentialException;
import uturismu.service.BookerManagementService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class BookerManagementTest {

	private static BookerManagementService bookerService;
	private static City city;

	public static void main(String[] args) {
	}

	@BeforeClass
	public static void init() {
		city = new City();
		city.setName("Springfield");
		city.setProvince("USA");
		ServiceFactory.getCityService().save(city);
		bookerService = ServiceFactory.getBookerManagementService();
	}

	/**
	 * Create an account and check whether the login service works.
	 */
	@Test
	public void checkCreationAndLogin() {
		String email = "test@volunia.eu";
		String password = "livuoiqueikiwiyankeecoikisayayaiani";
		String taxCode = "SSSHHH80S33H324L";
		// creates the account
		Account account = createAccount(email, password);
		// creates the booker
		Booker booker = createBooker(taxCode, account);
		// persist the account and the booker data
		Long id = bookerService.createAccount(account, booker);
		// log in the user
		account = bookerService.login(email, password);
		// asserts that the account is on the DB
		assertThat(account.getId(), is(equalTo(id)));
	}

	/**
	 * Create an account and checks whether the login service throws an
	 * InvalidCredentialException in response of an invalid password.
	 */
	@Test(expected = InvalidCredentialException.class)
	public void checkCreationAndLoginWithException() {
		String email = "account@volunia.eu";
		String password = "livuoiqueikiwiyankeecoikisayayaiani";
		String taxCode = "SSSHHH80S33H324L";
		String invalidPassword = "invalidpassword";
		// creates the account
		Account account = createAccount(email, password);
		// creates the booker
		Booker booker = createBooker(taxCode, account);
		// persist the account and the booker data
		bookerService.createAccount(account, booker);
		// try to log in, but the password is invalid. An exception is expected.
		account = bookerService.login(email, invalidPassword);
	}

	private static Account createAccount(String email, String password) {
		Account account = new Account();
		account.setActive(true);
		account.setEmail(email);
		String salt = HashUtil.generateSalt();
		account.setSalt(salt);
		account.setPassword(HashUtil.getHash(password, salt));
		account.setLastAccessTimestamp(new Date());
		account.setRegistrationTimestamp(new Date());
		account.setType(AccountType.BOOKER);
		return account;
	}

	private static Booker createBooker(String taxCode, Account account) {
		Booker booker = new Booker();
		booker.setFirstName("Name");
		booker.setLastName("Surname");
		booker.setTaxCode(taxCode);
		booker.setBirthDate(new Date());
		booker.setBirthPlace(city);
		booker.setGender(Gender.FEMALE);
		booker.setAccount(account);
		account.setBooker(booker);
		return booker;
	}

}