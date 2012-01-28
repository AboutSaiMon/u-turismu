package uturismu.dto;

import java.sql.Date;

import uturismu.dto.util.TransportType;

public class Transport extends Service {

	private static final long serialVersionUID = 5499793625458647910L;
	private Long id;
	private Date departureDate;
	private Date returnDate;
	private Boolean oneWay;
	private TransportType type;
	private Station departureStation;
	private Station arrivalStation;

}