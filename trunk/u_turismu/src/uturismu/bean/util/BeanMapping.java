package uturismu.bean.util;

import uturismu.bean.BookerBean;
import uturismu.bean.TourOperatorBean;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.dto.TourOperator;

public class BeanMapping {

	private BeanMapping() {
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