package uturismu.dto;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.annotations.Entity;

import uturismu.dto.util.AccommodationType;

@Entity
public class Accommodation implements Serializable {

	private static final long serialVersionUID = 226753614504147949L;
	private Long id;
	private String vatNumber; // partita IVA
	private String name;
	private Address address;
	private AccommodationType type;
	private Set<OvernightStay> overnightsStay;

}