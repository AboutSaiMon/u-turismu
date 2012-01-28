package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

public class POI implements Serializable {

	private static final long serialVersionUID = -1860221631532674110L;
	private Long id;
	private String name;
	private String description;
	private Address address;
	private Set<BookingService> bookingServices;

}