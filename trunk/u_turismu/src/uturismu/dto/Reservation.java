package uturismu.dto;

import java.util.Date;

public class Reservation extends Service {

	private static final long serialVersionUID = -1401386687925117984L;
	private Long id;
	private Date timeAndDate; // data e ora
	private Integer ticketsNumber;
	private POI poi; // point of interest

}
