package uturismu.bean.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import uturismu.bean.BookerBean;
import uturismu.bean.HolidayPackageBean;
import uturismu.bean.TourOperatorBean;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.HolidayPackage;
import uturismu.dto.Service;
import uturismu.dto.TourOperator;

public class BeanMapping {

	private BeanMapping() {
	}

	public static List<HolidayPackageBean> encode(Collection<HolidayPackage> collection) {
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