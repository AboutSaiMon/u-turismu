package uturismu.functional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static uturismu.ServiceFactory.getCityService;
import static uturismu.ServiceFactory.getTourOperatorService;
import static uturismu.ServiceFactory.getUserService;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import uturismu.HashUtil;
import uturismu.ServiceFactory;
import uturismu.dto.Account;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.OvernightStay;
import uturismu.dto.Service;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.ServiceType;
import uturismu.dto.enumtype.Status;
import uturismu.exception.InvalidCredentialException;

public class TourOperatorManagementTest {

	private static City city;

	@BeforeClass
	public static void init() {
		city = new City();
		city.setName("Cosenza");
		city.setProvince("Cosenza");
		getCityService().save(city);
	}

	@Test
	public void createTourOperatorAccount() {
		String email = "tourop@gmail.com";
		String password = "password";
		String vatNumber = "11111111110";
		Account account = createAccount(email, password);
		TourOperator tourOperator = createTourOperator(account, vatNumber, "UNO");
		getUserService().createUser(account, tourOperator);

		Account accountCopy = getUserService().logIn(email, password);
		assertThat(accountCopy, is(not(nullValue())));
		assertThat(accountCopy.getId(), is(equalTo(account.getId())));
	}

	@Test(expected = InvalidCredentialException.class)
	public void createTourOperatorWithException() {
		String email = "touroperatorException@gmail.com";
		String password = "password";
		String vatNumber = "12345678901";
		Account account = createAccount(email, password);
		TourOperator tourOperator = createTourOperator(account, vatNumber, "UNO");
		getUserService().createUser(account, tourOperator);
		password = "passwordSbagliata";
		account = getUserService().logIn(email, password);
	}

	@Test
	public void queryHolidayPackageTest() {
		String email = "tourop@gmail.com";
		String password = "password";
		String vatNumber = "22222222220";
		Account account1 = createAccount(email, password);
		TourOperator top1 = createTourOperator(account1, vatNumber, "UNO");
		top1.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package1", top1));
		top1.addHolidayPackage(createHolidayPackage(Status.EXPIRED, "Package2", top1));
		top1.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package3", top1));
		getUserService().createUser(account1, top1);
		Long idTop1 = top1.getId();

		String email2 = "tourOperatorDUE@gmail.com";
		String password2 = "passwordDUE";
		String vatNumber2 = "01333333333";
		Account account2 = createAccount(email2, password2);
		TourOperator top2 = createTourOperator(account2, vatNumber2, "DUE");
		top2.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package2_1", top2));
		top2.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package2_2", top2));
		top2.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package2_3", top2));
		getUserService().createUser(account2, top2);
		Long idTop2 = top2.getId();

		List<HolidayPackage> result = null;

		result = getTourOperatorService().findAllHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(3)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
		}

		result = getTourOperatorService().findPublishedHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.PUBLISHED)));
		}

		result = getTourOperatorService().findDraftHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.DRAFT)));
		}

		result = getTourOperatorService().findExpiredHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.EXPIRED)));
		}

		result = getTourOperatorService().findAllHolidayPackages(idTop2);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(3)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top2.getId())));
		}

		result = getTourOperatorService().findPublishedHolidayPackages(idTop2);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(2)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top2.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.PUBLISHED)));
		}

		result = getTourOperatorService().findDraftHolidayPackages(idTop2);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top2.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.DRAFT)));
		}
	}

	@Test
	public void updateHolidayPackage() {
		String email = "TESTING_UPDATE@gmail.com";
		String password = "password";
		String vatNumber = "98765432122";
		Account account1 = createAccount(email, password);
		TourOperator tourOperator = createTourOperator(account1, vatNumber, "UNO");
		tourOperator.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package1", tourOperator));
		tourOperator.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package2", tourOperator));
		tourOperator.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package3",
				tourOperator));
		getUserService().createUser(account1, tourOperator);

		List<HolidayPackage> result = null;
		result = getTourOperatorService().findDraftHolidayPackages(tourOperator.getId());
		assertThat(result.size(), is(equalTo(2)));

		HolidayPackage hpToUpdate = result.get(1);
		String description = "setto la descrizione di update ";
		hpToUpdate.setDescription(description);
		hpToUpdate.setAvailability(10);
		hpToUpdate.setStatus(Status.PUBLISHED);
		getTourOperatorService().updateHolidayPackage(hpToUpdate);

		result = getTourOperatorService().findDraftHolidayPackages(tourOperator.getId());
		assertThat(result.size(), is(equalTo(1)));
		result = getTourOperatorService().findPublishedHolidayPackages(tourOperator.getId());
		
		result = getTourOperatorService().findDraftHolidayPackages(tourOperator.getId());
		hpToUpdate = result.get(0);
		
		OvernightStay service = new OvernightStay();
		service.setArrivalDate(new Date());
		service.setDescription("L'hotel ha la vista sul mare");
		service.setPrice(100.0);
		service.setLeavingDate(new Date());
		service.setServiceType(ServiceType.ONLY_BREAKFAST);
		service.setHolidayPackage(hpToUpdate);
		
		ServiceFactory.getOvernightStayService().save(service);
	}

	private HolidayPackage createHolidayPackage(Status status, String Name, TourOperator top) {
		HolidayPackage hp = new HolidayPackage();
		hp.setAvailability(3);
		hp.setCustomerNumber(2);
		hp.setStatus(status);
		hp.setDueDate(new Date());
		hp.setName("pacchettovacanze_" + status + Name);
		return hp;
	}

	private TourOperator createTourOperator(Account account, String vatNumber, String name) {
		TourOperator to = new TourOperator();
		to.setHolderName("pippo Inzaghi");
		to.setName("Company " + name);
		to.setVatNumber(vatNumber);
		to.setAccount(account);
		account.setTourOperator(to);
		return to;
	}

	private TourOperator createTourOperatorEX(Account account) {
		TourOperator to = new TourOperator();
		to.setHolderName("Eccezzione");
		to.setName("io Sono un eccezzione");
		to.setVatNumber("11111111111");
		to.setAccount(account);
		account.setTourOperator(to);
		return to;
	}

	private Account createAccount(String email, String password) {
		Account account = new Account();
		account.setActive(true);
		account.setEmail(email);
		String salt = HashUtil.generateSalt();
		account.setSalt(salt);
		String psw = HashUtil.getHash(password, salt);
		account.setPassword(psw);
		account.setType(AccountType.TOUR_OPERATOR);
		account.setLastAccessTimestamp(new Date());
		account.setRegistrationTimestamp(new Date());
		return account;
	}

}
