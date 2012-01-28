package uturismu.dto;

import java.sql.Date;

public class Transport extends Service {

	private static final long serialVersionUID = 5499793625458647910L;
	private Long id;
	private Date departureDate;
	private Date returnDate;
	private Boolean oneWay;
	private String company;
	private Station departureStation;
	private Station arrivalStation;

}