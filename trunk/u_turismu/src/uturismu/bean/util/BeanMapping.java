package uturismu.bean.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import uturismu.bean.BookerBean;
import uturismu.bean.CityBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.TourOperatorBean;
import uturismu.bean.TourOperatorSignup;
import uturismu.dto.Account;
import uturismu.dto.Address;
import uturismu.dto.Booker;
import uturismu.dto.City;
import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;

public class BeanMapping {

	private BeanMapping() {
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

	public static Account getAccount(TourOperatorSignup bean) {
		Account account = new Account();
		account.setEmail(bean.getEmail());
		account.setPassword(bean.getPassword());
		account.setType(AccountType.TOUR_OPERATOR);
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
		bean.setType(account.getType());

		bean.setTaxCode(booker.getTaxCode());
		bean.setFirstName(booker.getFirstName());
		bean.setLastName(booker.getLastName());
		return bean;
	}

	public static TourOperatorBean encode(Account account, TourOperator tourOperator) {
		TourOperatorBean bean = new TourOperatorBean();
		bean.setUserId(tourOperator.getId());
		bean.setEmail(account.getEmail());
		bean.setType(account.getType());

		bean.setVatNumber(tourOperator.getVatNumber());
		bean.setName(tourOperator.getName());
		bean.setHolderName(tourOperator.getHolderName());
		return bean;
	}

}