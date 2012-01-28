package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public class HolidayPackage implements Serializable {

	private static final long serialVersionUID = -138153679026481915L;
	private Long id;
	private String name; // unique
	private String description;
	private TourOperator tourOperator; // unique
	private Set<Booking> bookings;
	private Set<Service> services;

}
