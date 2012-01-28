package uturismu.dto;

import java.util.Date;

import uturismu.dto.util.ServiceType;

public class OvernightStay extends Service {

	private static final long serialVersionUID = 1066966536889642801L;
	private Long id;
	private Date arrivalDate;
	private Date leavingDate;
	private Integer guestsNumber;
	private ServiceType serviceType;
	private Accommodation accomodation;

}
