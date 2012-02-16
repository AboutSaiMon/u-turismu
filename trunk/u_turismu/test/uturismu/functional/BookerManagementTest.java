package uturismu.functional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static uturismu.ServiceFactory.getUserService;

import java.util.Date;

import org.junit.Test;

import uturismu.HashUtil;
import uturismu.ServiceFactory;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.Status;

public class BookerManagementTest {

	@Test
	public void creaBooker() {
		String email = "lollo@yahoo.com";
		String password = "ilario";
		String tax = "SSSLDKJ8KLKJS";
		String firstName = "Laura";
		String lastName = "Giollo";
		Account account = createAccount(email, password, AccountType.BOOKER);
		Booker booker = createBooker(account, tax, firstName, lastName);
		getUserService().createAccount(account, booker);
		
		Account accountCopy = getUserService().logIn(email, password);
		assertThat(accountCopy, is(not(nullValue())));
		assertThat(accountCopy.getId(), is(equalTo(account.getId())));
		
		email = "percoco@paglietta.it";
		password = "sferistereo";
		String vatNumber = "0A100111001";
		account = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator tourOperator = createTourOperator(vatNumber, "Vai a cagare", account);
		getUserService().createAccount(account, tourOperator);
		
		HolidayPackage holiday = createHolidayPackage("Zuzurra", Status.PUBLISHED, tourOperator);
		ServiceFactory.getTourOperatorService().createHolidayPackage(holiday);
		
		ServiceFactory.getBookerService().book(booker, holiday);
	}

	private Booker createBooker(Account account, String tax, String firstName, String lastName) {
		Booker booker = new Booker();
		booker.setAccount(account);
		account.setBooker(booker);
		booker.setTaxCode(tax);
		booker.setFirstName(firstName);
		booker.setLastName(lastName);
		booker.setBirthDate(new Date());
		return booker;
	}

	private static Account createAccount(String email, String password, AccountType type) {
		Account account = new Account();
		account.setActive(true);
		account.setEmail(email);
		String salt = HashUtil.generateSalt();
		account.setSalt(salt);
		account.setPassword(HashUtil.getHash(password, salt));
		account.setLastAccessTimestamp(new Date());
		account.setRegistrationTimestamp(new Date());
		account.setType(type);
		return account;
	}
	
	private static TourOperator createTourOperator(String vatNumber, String name, Account account) {
		TourOperator tourOperator = new TourOperator();
		tourOperator.setVatNumber(vatNumber);
		tourOperator.setName(name);
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
		pack.setTourOperator(tourOperator);
		tourOperator.addHolidayPackage(pack);
		return pack;
	}

}
