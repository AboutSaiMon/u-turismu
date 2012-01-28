package uturismu.dto;

import java.io.Serializable;
import java.util.Date;

import uturismu.dto.util.AccountType;

public class Account implements Serializable {

	private static final long serialVersionUID = 1902321524953220029L;
	private Long id;
	private String email; // unique
	private String password;
	private String salt;
	private Date registrationDate;
	private Date lastAccessDate;
	private Boolean active;
	private AccountType type;
	private TourOperator tourOperator;
	private Customer customer;

}