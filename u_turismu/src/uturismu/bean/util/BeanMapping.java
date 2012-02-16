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
		return bean;
	}

	public static TourOperatorBean encode(Account account, TourOperator tourOperator) {
		TourOperatorBean bean = new TourOperatorBean();
		return bean;
	}

}