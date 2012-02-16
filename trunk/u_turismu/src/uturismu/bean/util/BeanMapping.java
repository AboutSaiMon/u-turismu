package uturismu.bean.util;

import uturismu.bean.AddressBean;
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
		bean.setBirthDate(booker.getBirthDate());
		bean.setBirthPlaceId(booker.getBirthPlace().getId());
		bean.setEmail(account.getEmail());
		bean.setFirstName(booker.getFirstName());
		bean.setGender(booker.getGender());
		bean.setIdentificationDocumentNumber(booker.getIdentificationDocumentNumber());
		bean.setIdentificationDocumentType(booker.getIdentificationDocumentType());
		bean.setIssuingAuthority(booker.getIssuingAuthority());
		bean.setLastName(booker.getLastName());
		AddressBean address = new AddressBean();
		address.setCityId(booker.getResidence().getCity().getId());
		address.setStreet(booker.getResidence().getStreet());
		address.setZipCode(booker.getResidence().getZipCode());
		bean.setResidence(address);
		bean.setTaxCode(booker.getTaxCode());
		bean.setType(account.getType());
		bean.setUserId(booker.getId());
		return bean;
	}

	public static TourOperatorBean encode(Account account, TourOperator tourOperator) {
		TourOperatorBean bean = new TourOperatorBean();
		bean.setEmail(account.getEmail());
		AddressBean address = new AddressBean();
		address.setCityId(tourOperator.getHeadOffice().getCity().getId());
		address.setStreet(tourOperator.getHeadOffice().getStreet());
		address.setZipCode(tourOperator.getHeadOffice().getZipCode());
		bean.setHeadOffice(address);
		bean.setHolderName(tourOperator.getHolderName());
		bean.setName(tourOperator.getName());
		bean.setType(account.getType());
		bean.setUserId(tourOperator.getId());
		bean.setVatNumber(tourOperator.getVatNumber());
		return bean;
	}

}