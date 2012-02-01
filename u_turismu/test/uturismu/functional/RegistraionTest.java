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

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import uturismu.ApplicationContextFactory;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.util.AccountType;
import uturismu.service.AccountService;
import uturismu.service.BookerService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class RegistraionTest {

	private static ApplicationContext context;

	@BeforeClass
	public static void init() {
		context = ApplicationContextFactory.getApplicationContext();
	}

	@Test
	public void tryToRegisterAnAccount() {
		Account account = new Account();
		account.setEmail("prova@gmail.com");
		account.setPassword("ciuccia");
		account.setLastAccessTimestamp(new Date());
		account.setRegistrationTimestamp(new Date());
		account.setSalt("salt");
		account.setActive(true);
		account.setType(AccountType.ADMINISTRATOR);
		
		Booker booker = new Booker();
		booker.setAccount(account);
		
		account.setBooker(booker);
		
		ApplicationContext context = ApplicationContextFactory.getApplicationContext();
		AccountService service = context.getBean(AccountService.class);
		service.save(account);
		BookerService bookerService = context.getBean(BookerService.class);
		bookerService.save(booker);
		
		
		
	}
}