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
import uturismu.dto.Customer;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IdType;
import uturismu.exception.ExceptionMessages;
import uturismu.exception.InvalidCredentialException;
import uturismu.service.BookerManagementService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class BookerManagementTest {

	private static BookerManagementService bookerService;
	private static City city;

	@BeforeClass
	public static void init() {
		city = new City();
		city.setName("Springfield");
		city.setProvince("USA");
		ServiceFactory.getCityService().save(city);
		bookerService = ServiceFactory.getBookerManagementService();
	}

	@Test
	public void createBookerAccount() {
		String email = "account@gmail.com";
		String password = "livuoiqueikiwiyankeecoikisayayaiani";
		Account account = createAccount(email, password);
		Booker booker = createBooker(account);
		Long id = bookerService.createAccount(account, booker);
		try {
			account = bookerService.login(email, password);
			assertThat(account.getId(), is(equalTo(id)));
		} catch (InvalidCredentialException e) {
			assertThat(e.getMessage(), is(equalTo(ExceptionMessages.INCORRECT_ACCOUNT)));
		}
	}

	private Account createAccount(String email, String password) {
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

	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setTaxCode("SMPHMR89T31Z404B");
		customer.setFirstName("Homer");
		customer.setLastName("Simpson");
		customer.setGender(Gender.MALE);
		customer.setBirthPlace(city);
		customer.setBirthDate(new Date());
		customer.setIdentificationDocumentNumber("1103D2");
		customer.setIdentificationDocumentType(IdType.PASSPORT);
		customer.setIssuingAuthority("Police");
		return customer;
	}

	private Booker createBooker(Account account) {
		Booker booker = new Booker();
		account.setBooker(booker);
		booker.setAccount(account);
		return booker;
	}

}