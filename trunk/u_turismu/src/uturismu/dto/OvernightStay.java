package uturismu.dto;

import java.util.Date;

import uturismu.dto.util.IncludedService;

public class OvernightStay extends Service {

	private static final long serialVersionUID = 1066966536889642801L;
	private Long id;
	private Date entryDate;
	private Date leavingDate;
	private Integer personNumber; // si riferisce al numero di posti letto
	private IncludedService includedService; // mezza pension, solo colazione,
												// ecc
	private Accommodation accomodation;

}
