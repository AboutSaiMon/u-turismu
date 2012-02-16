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
import java.util.Set;

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
import uturismu.exception.AccountException;

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
		Account account = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator tourOperator = createTourOperator(vatNumber, "UNO", account);
		getUserService().createAccount(account, tourOperator);

		Account accountCopy = getUserService().logIn(email, password);
		assertThat(accountCopy, is(not(nullValue())));
		assertThat(accountCopy.getId(), is(equalTo(account.getId())));

	}

	@Test(expected = AccountException.class)
	public void createTourOperatorWithException() {
		String email = "touroperatorException@gmail.com";
		String password = "password";
		String vatNumber = "12345678901";
		Account account = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator tourOperator = createTourOperator(vatNumber, "UNO", account);
		getUserService().createAccount(account, tourOperator);
		password = "passwordSbagliata";
		account = getUserService().logIn(email, password);
	}

	@Test
	public void queryHolidayPackageTest() {
		String email = "touroperator@email.com";
		String password = "password";
		String vatNumber = "22222222220";
		Account account1 = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator top1 = createTourOperator(vatNumber, "UNO", account1);
		top1.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package1", top1));
		top1.addHolidayPackage(createHolidayPackage(Status.EXPIRED, "Package2", top1));
		top1.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package3", top1));
		getUserService().createAccount(account1, top1);
		Long idTop1 = top1.getId();

		String email2 = "tourOperatorDUE@gmail.com";
		String password2 = "passwordDUE";
		String vatNumber2 = "01333333333";
		Account account2 = createAccount(email2, password2, AccountType.TOUR_OPERATOR);
		TourOperator top2 = createTourOperator(vatNumber2, "DUE", account2);
		top2.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package2_1", top2));
		top2.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package2_2", top2));
		top2.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package2_3", top2));
		getUserService().createAccount(account2, top2);
		Long idTop2 = top2.getId();

		List<HolidayPackage> result = null;

		result = getTourOperatorService().getAllHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(3)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
		}

		result = getTourOperatorService().getPublishedHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.PUBLISHED)));
		}

		result = getTourOperatorService().getDraftHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.DRAFT)));
		}

		result = getTourOperatorService().getExpiredHolidayPackages(idTop1);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top1.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.EXPIRED)));
		}

		result = getTourOperatorService().getAllHolidayPackages(idTop2);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(3)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top2.getId())));
		}

		result = getTourOperatorService().getPublishedHolidayPackages(idTop2);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(2)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top2.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.PUBLISHED)));
		}

		result = getTourOperatorService().getDraftHolidayPackages(idTop2);
		assertThat(result, is(not(nullValue())));
		assertThat(result.size(), is(equalTo(1)));
		for (HolidayPackage holidayPackage : result) {
			assertThat(holidayPackage.getTourOperator().getId(), is(equalTo(top2.getId())));
			assertThat(holidayPackage.getStatus(), is(equalTo(Status.DRAFT)));
		}
	}

	@Test
	public void updateHolidayPackage() {
		String email = "TESTING_UPDATE@yahoo.com";
		String password = "password";
		String vatNumber = "98765432122";
		Account account1 = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator tourOperator = createTourOperator(vatNumber, "UNO", account1);
		tourOperator.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package1", tourOperator));
		tourOperator.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package2", tourOperator));
		tourOperator.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package3",
				tourOperator));
		getUserService().createAccount(account1, tourOperator);

		List<HolidayPackage> result = null;
		result = getTourOperatorService().getDraftHolidayPackages(tourOperator.getId());
		assertThat(result.size(), is(equalTo(2)));

		HolidayPackage hpToUpdate = result.get(1);
		String description = "setto la descrizione di update ";
		hpToUpdate.setDescription(description);
		hpToUpdate.setAvailability(10);
		hpToUpdate.setStatus(Status.PUBLISHED);
		getTourOperatorService().updateHolidayPackage(hpToUpdate);

		result = getTourOperatorService().getDraftHolidayPackages(tourOperator.getId());
		assertThat(result.size(), is(equalTo(1)));
		result = getTourOperatorService().getPublishedHolidayPackages(tourOperator.getId());

		result = getTourOperatorService().getDraftHolidayPackages(tourOperator.getId());
		hpToUpdate = result.get(0);

		OvernightStay service = new OvernightStay();
		service.setArrivalDate(new Date());
		service.setDescription("L'hotel ha la vista sul mare");
		service.setPrice(100.0);
		service.setLeavingDate(new Date());
		service.setServiceType(ServiceType.ONLY_BREAKFAST);
		service.setHolidayPackage(hpToUpdate);

		hpToUpdate.setStatus(Status.PUBLISHED);
		getTourOperatorService().updateHolidayPackage(hpToUpdate);
		ServiceFactory.getOvernightStayService().save(service);

		List<HolidayPackage> hpResult = getTourOperatorService().getAllHolidayPackages(
				tourOperator.getId());

		for (HolidayPackage holidayPackage : hpResult) {
			System.out.print("Pack :" + holidayPackage.getName() + " " + holidayPackage.getStatus()
					+ " services : ");
			Set<Service> services = holidayPackage.getServices();
			for (Service service2 : services) {
				System.out.print(" " + service2.getPrice());
			}
			System.out.println();
		}
	}

	@Test
	public void deletePackage() {
		String email = "TESTING_DELETE@gmail.com";
		String password = "password";
		String vatNumber = "400567";
		Account account1 = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator tourOperator = createTourOperator(vatNumber, "UNO", account1);
		tourOperator.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package1", tourOperator));
		tourOperator.addHolidayPackage(createHolidayPackage(Status.DRAFT, "Package2", tourOperator));
		tourOperator.addHolidayPackage(createHolidayPackage(Status.PUBLISHED, "Package3",
				tourOperator));
		getUserService().createAccount(account1, tourOperator);

		HolidayPackage toDelete = getTourOperatorService().getDraftHolidayPackages(
				tourOperator.getId()).get(0);
		Long deletedPackID = toDelete.getId();
		getTourOperatorService().deleteHolidayPackage(toDelete);
		assertThat(getUserService().getHolidayPackageByID(deletedPackID), is(nullValue()));
	}

	@Test
	public void addPackageTest() {
		String email = "TESTING_ADD@gmail.com";
		String password = "password";
		String vatNumber = "789";
		Account account1 = createAccount(email, password, AccountType.TOUR_OPERATOR);
		TourOperator tourOperator = createTourOperator(vatNumber, "UNO", account1);
		getUserService().createAccount(account1, tourOperator);

		HolidayPackage hpToADD = createHolidayPackage(Status.DRAFT, "PackAdd", tourOperator);

		Long hpID = getTourOperatorService().createHolidayPackage(hpToADD);

		List<HolidayPackage> list = getTourOperatorService().getAllHolidayPackages(
				tourOperator.getId());
		hpToADD = getUserService().getHolidayPackageByID(hpID);

		assertThat(hpToADD.getTourOperator().getId(), is(equalTo(tourOperator.getId())));
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
		tourOperator.setName("TourOperator" + name);
		tourOperator.setHolderName("Dr. Lemuel Gulliver");
		tourOperator.setAccount(account);
		account.setTourOperator(tourOperator);
		return tourOperator;
	}

	private static HolidayPackage createHolidayPackage(Status status, String name,
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
