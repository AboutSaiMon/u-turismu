package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public abstract class Service implements Serializable {

	private static final long serialVersionUID = 3469323821178357386L;
	private Long id;
	private Double price;
	private Set<HolidayPackage> holidayPackages;

}
