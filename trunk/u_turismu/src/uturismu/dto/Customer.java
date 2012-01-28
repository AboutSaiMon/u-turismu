package uturismu.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import org.hibernate.annotations.Entity;

import uturismu.dto.util.Gender;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = -4043855751210103797L;
	private Long id;
	private String taxCode;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private City birthPlace;
	private Address livingPlace;
	private Gender gender;
	private Account account;
	private Set<Booking> bookings;

}
