package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public class HolidayPackage implements Serializable {

	private static final long serialVersionUID = -138153679026481915L;
	private Long id;
	private String name;
	private String description;
	private TourOperator tourOperator;
	private Set<Booking> bookings;
	private Set<Service> services;

}
