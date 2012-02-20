package uturismu.bean.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import uturismu.bean.BookerBean;
import uturismu.bean.BookerSignup;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.TourOperatorBean;
import uturismu.bean.TourOperatorSignup;
import uturismu.bean.TourOperatorUpdate;
import uturismu.bean.UserSignup;
import uturismu.dto.Account;
import uturismu.dto.Address;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.dto.enumtype.Gender;
import uturismu.dto.enumtype.IdType;

public class BeanMapping {

	private BeanMapping() {
	}

	public static Booker getBooker(BookerSignup bean, City birthPlace, City residenceCity) {
		Booker dto = new Booker();
		dto.setTaxCode(bean.getTaxCode());
		dto.setFirstName(bean.getFirstName());
		dto.setLastName(bean.getLastName());
		// acquisisce la stringa che denota il sesso
		String gender = bean.getGender();
		if (gender.equals("m")) {
			dto.setGender(Gender.MALE);
		} else if (gender.equals("f")) {
			dto.setGender(Gender.FEMALE);
		} else if (gender.equals("-")) {
			dto.setGender(Gender.NOT_SPECIFIED);
		}
		// setta la città con un oggetto City di tipo DTO
		dto.setBirthPlace(birthPlace);
		// acquisisce il giorno/mese/anno di nascita
		Integer day = bean.getBirthDay();
		Integer month = bean.getBirthMonth();
		Integer year = bean.getBirthYear();
		// acquisisce un'istanza di Calendare e setta la data corretta
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		dto.setBirthDate(calendar.getTime());
		// setta l'indirizzo
		Address address = new Address();
		address.setStreet(bean.getStreet());
		address.setZipCode(bean.getZipCode());
		address.setCity(residenceCity);
		dto.setResidence(address);
		// acquisisce la stringa che denota il tipo di documento
		String documentType = bean.getIdentificationDocumentType();
		if (documentType.equals("id")) {
			dto.setIdentificationDocumentType(IdType.ID);
		} else if (documentType.equals("pat")) {
			dto.setIdentificationDocumentType(IdType.DRIVER_LICENSE);
		} else if (documentType.equals("pas")) {
			dto.setIdentificationDocumentType(IdType.PASSPORT);
		} else if (documentType.equals("vis")) {
			dto.setIdentificationDocumentType(IdType.VISA);
		}
		dto.setIdentificationDocumentNumber(bean.getIdentificationDocumentNumber());
		dto.setIssuingAuthority(bean.getIssuingAuthority());
		return dto;
	}

	public static TourOperator getTourOperator(TourOperatorSignup bean, City city) {
		TourOperator dto = new TourOperator();
		dto.setName(bean.getName());
		dto.setVatNumber(bean.getVatNumber());
		dto.setHolderName(bean.getHolderName());
		Address address = new Address();
		address.setStreet(bean.getStreet());
		address.setZipCode(bean.getZipCode());
		address.setCity(city);
		dto.setHeadOffice(address);
		return dto;
	}

	public static Account getAccount(UserSignup bean) {
		Account account = new Account();
		account.setEmail(bean.getEmail());
		account.setPassword(bean.getPassword());
		return account;
	}

	public static List<CityBean> encode(Collection<City> collection) {
		List<CityBean> cityList = new ArrayList<CityBean>(collection.size());
		for (City c : collection) {
			cityList.add(encode(c));
		}
		return cityList;
	}

	public static CityBean encode(City city) {
		CityBean bean = new CityBean();
		bean.setId(city.getId());
		bean.setName(city.getName());
		return bean;
	}

	public static List<HolidayPackageBean> encode(List<HolidayPackage> collection) {
		List<HolidayPackageBean> holidayPackageList = new ArrayList<HolidayPackageBean>(
				collection.size());
		for (HolidayPackage h : collection) {
			holidayPackageList.add(encode(h));
		}
		return holidayPackageList;
	}

	public static HolidayPackageBean encode(HolidayPackage holidayPackage) {
		HolidayPackageBean bean = new HolidayPackageBean();
		bean.setId(holidayPackage.getId());
		bean.setName(holidayPackage.getName());
		bean.setDescription(holidayPackage.getDescription());
		Set<Service> services = holidayPackage.getServices();
		Double price = 0.0;
		for (Service service : services) {
			price += service.getPrice();
		}
		bean.setPrice(price);
		return bean;
	}

	public static BookerBean encode(Account account, Booker booker) {
		BookerBean bean = new BookerBean();
		bean.setUserId(booker.getId());
		bean.setEmail(account.getEmail());
		bean.setType(AccountType.BOOKER);

		bean.setTaxCode(booker.getTaxCode());
		bean.setFirstName(booker.getFirstName());
		bean.setLastName(booker.getLastName());
		return bean;
	}

	public static TourOperatorBean encode(Account account, TourOperator tourOperator) {
		TourOperatorBean bean = new TourOperatorBean();
		Long id = tourOperator.getId();
		bean.setUserId(id);
		bean.setEmail(account.getEmail());
		bean.setType(AccountType.TOUR_OPERATOR);

		bean.setVatNumber(tourOperator.getVatNumber());
		bean.setName(tourOperator.getName());
		bean.setHolderName(tourOperator.getHolderName());
		return bean;
	}

	public static TourOperatorUpdate encodeTourOperatorUpdate(Account account,
			TourOperator tourOperator) {
		TourOperatorUpdate bean = new TourOperatorUpdate();
		bean.setCity(tourOperator.getHeadOffice().getCity().getId());
		bean.setEmail(account.getEmail());
		bean.setHolderName(tourOperator.getHolderName());
		bean.setName(tourOperator.getName());
		// bean.setPassword(account.getPassword());
		bean.setStreet(tourOperator.getHeadOffice().getStreet());
		bean.setVatNumber(tourOperator.getVatNumber());
		bean.setZipCode(tourOperator.getHeadOffice().getZipCode());
		return bean;

	}

}