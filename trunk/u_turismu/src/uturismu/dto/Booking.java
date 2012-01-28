package uturismu.dto;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {

	private static final long serialVersionUID = 7295908518751530161L;
	private Long id;
	private Date reservationDate; // unique
	private Customer customer; // unique
	private HolidayPackage holidayPackage;
	
	
}
