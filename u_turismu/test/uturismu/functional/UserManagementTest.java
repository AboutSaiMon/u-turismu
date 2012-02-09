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
import static uturismu.ServiceFactory.getCityService;
import static uturismu.ServiceFactory.getUserManagementService;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import uturismu.HashUtil;
import uturismu.ServiceFactory;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.HolidayTag;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.Status;
import uturismu.exception.InvalidCredentialException;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class UserManagementTest {

	private static City city;

	@BeforeClass
	public static void init() {
		city = new City();
		city.setName("Springfield");
		city.setProvince("USA");
		getCityService().save(city);
	}

	/**
	 * Create a booker account and check whether the login service works.
	 */
	@Test
	@Ignore
	public void createBookerAccountAndLogin() {
		String email = "test@volunia.eu";
		String password = "livuoiqueikiwiyankeecoikisayayaiani";
		String taxCode = "SSSHHH80S33H324L";
		// creates the account
		Account account = createAccount(email, password);
		// creates the booker
		Booker booker = createBooker(taxCode, account);
		// persist the account and the booker objects
		getUserManagementService().createAccount(account, booker);
		// log in the user
		Account sameAccount = getUserManagementService().login(email, password);
		// asserts that the account is on the DB
		assertThat(account.getId(), is(equalTo(sameAccount.getId())));
	}

	/**
	 * Create a booker account and checks whether the login service throws an
	 * InvalidCredentialException in response of an invalid password.
	 */
	@Test(expected = InvalidCredentialException.class)
	@Ignore
	public void createBookerAccountAndLoginWithException() {
		String email = "account@volunia.eu";
		String password = "livuoiqueikiwiyankeecoikisayayaiani";
		String taxCode = "GPGLLL11D89G039A";
		String invalidPassword = "invalidpassword";
		// creates the account
		Account account = createAccount(email, password);
		// creates the booker
		Booker booker = createBooker(taxCode, account);
		// persist the account and the booker data
		getUserManagementService().createAccount(account, booker);
		// try to log in, but the password is invalid. An exception is expected.
		account = getUserManagementService().login(email, invalidPassword);
	}

	@Test
	public void showHolidayPackage() {
		/*
		 * // retrieves all the holiday tags List<HolidayTag> tags =
		 * ServiceFactory.getHolidayTagService().findAll(); // create the first
		 * account Account account1 = createAccount("email1", "password1");
		 * TourOperator to1 = createTourOperator("vat1", account1); // create the
		 * second account Account account2 = createAccount("email2", "password2");
		 * TourOperator to2 = createTourOperator("vat2", account2); // create five
		 * holiday package and two of those aren't published yet. HolidayPackage
		 * pack1 = createHolidayPackage("pack1", Status.PUBLISHED, to1);
		 * pack1.addHolidayTag(tags.get(0)); pack1.addHolidayTag(tags.get(1));
		 * pack1.addHolidayTag(tags.get(2)); pack1.addHolidayTag(tags.get(3));
		 * HolidayPackage pack2 = createHolidayPackage("pack2", Status.PUBLISHED,
		 * to1); pack2.addHolidayTag(tags.get(0)); HolidayPackage pack3 =
		 * createHolidayPackage("pack3", Status.DRAFT, to1);
		 * pack3.addHolidayTag(tags.get(1)); HolidayPackage pack4 =
		 * createHolidayPackage("pack4", Status.PUBLISHED, to2);
		 * pack4.addHolidayTag(tags.get(2)); HolidayPackage pack5 =
		 * createHolidayPackage("pack5", Status.DRAFT, to2);
		 * pack5.addHolidayTag(tags.get(3)); // save the detached objects on the
		 * DB getUserManagementService().createAccount(account1, to1);
		 * getUserManagementService().createAccount(account2, to2);
		 * ServiceFactory.getHolidayPackageService().save(pack1);
		 * ServiceFactory.getHolidayPackageService().save(pack2);
		 * ServiceFactory.getHolidayPackageService().save(pack3);
		 * ServiceFactory.getHolidayPackageService().save(pack4);
		 * ServiceFactory.getHolidayPackageService().save(pack5);
		 * 
		 * // the total amount of published holiday packages is 3, even if the
		 * total // rows number in the HolidayPackage table is 5.
		 * assertThat(getUserManagementService().getHolidayPackages().size(),
		 * is(3)); // the published packages of the first tour operator are two
		 * assertThat
		 * (getUserManagementService().getHolidayPackagesByTourOperator(1L
		 * ).size(), is(2)); // the selected holiday packages will be: pack1,
		 * pack2, pack3 List<HolidayPackage> packages =
		 * ServiceFactory.getUserManagementService()
		 * .getHolidayPackagesByTags(tags.get(0).getId(), tags.get(1).getId());
		 */

		// creo il primo account
		Account account1 = createAccount("email@prova.it", "password1");
		TourOperator to1 = createTourOperator("111DK2", account1);
		to1.setId(1L);
		// creo il secondo account
		Account account2 = createAccount("ciccio@prova.it", "password2");
		TourOperator to2 = createTourOperator("111DK3", account2);
		to2.setId(2L);
		// creo un holiday package
		HolidayPackage pack1 = createHolidayPackage("Pack 1", Status.PUBLISHED, to1);
		pack1.setId(1L);
		HolidayPackage pack2 = createHolidayPackage("Pack 2", Status.PUBLISHED, to1);
		pack2.setId(2L);
		HolidayPackage pack3 = createHolidayPackage("Pack 3", Status.PUBLISHED, to2);
		pack3.setId(3L);

		HolidayTag tag1 = new HolidayTag();
		tag1.setId(1L);
		tag1.setName("tag1");
		tag1.setDescription("desc1");
		HolidayTag tag2 = new HolidayTag();
		tag2.setId(2L);
		tag2.setName("tag2");
		tag2.setDescription("desc2");

		pack1.addHolidayTag(tag1);
		pack1.addHolidayTag(tag2);
		pack2.addHolidayTag(tag2);
		pack3.addHolidayTag(tag1);

		ServiceFactory.getUserManagementService().createAccount(account1, to1);
		ServiceFactory.getUserManagementService().createAccount(account2, to2);

		HolidayPackage pack4 = createHolidayPackage("Pack 4", Status.PUBLISHED, to2);
		pack4.setId(4L);
		pack4.addHolidayTag(tag1);
		ServiceFactory.getHolidayPackageService().save(pack4);
		
		List<HolidayPackage> list = ServiceFactory.getUserManagementService().getHolidayPackagesByTags(tag1.getId());
		for( HolidayPackage h : list) {
			System.out.println(h.getName());
		}
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

	private static TourOperator createTourOperator(String vatNumber, Account account) {
		TourOperator tourOperator = new TourOperator();
		tourOperator.setVatNumber(vatNumber);
		tourOperator.setName("Gulliver Travel");
		tourOperator.setHolderName("Dr. Lemuel Gulliver");
		tourOperator.setAccount(account);
		account.setTourOperator(tourOperator);
		return tourOperator;
	}

	private static HolidayPackage createHolidayPackage(String name, Status status,
			TourOperator tourOperator) {
		HolidayPackage pack = new HolidayPackage();
		pack.setName(name);
		pack.setStatus(status);
		pack.setDueDate(new Date());
		pack.setCustomerNumber(2);
		pack.setAvailability(10);
		tourOperator.addHolidayPackage(pack);
		return pack;
	}

}