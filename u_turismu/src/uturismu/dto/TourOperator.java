package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public class TourOperator implements Serializable {

	private static final long serialVersionUID = 4132492228184160094L;
	private Long id;
	private String vatNumber; // unique
	private String name;
	private Address headOffice;
	private Account account;
	private Set<HolidayPackage> holidayPackages;
	

}
