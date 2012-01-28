package uturismu.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Entity;

import uturismu.dto.util.AccountType;

@Entity
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
	
	public Account() {
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public Boolean getActive() {
		return active;
	}

	public AccountType getType() {
		return type;
	}

	public TourOperator getTourOperator() {
		return tourOperator;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void setTourOperator(TourOperator tourOperator) {
		this.tourOperator = tourOperator;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((lastAccessDate == null) ? 0 : lastAccessDate.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime
				* result
				+ ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
		result = prime * result
				+ ((tourOperator == null) ? 0 : tourOperator.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (lastAccessDate == null) {
			if (other.lastAccessDate != null)
				return false;
		} else if (!lastAccessDate.equals(other.lastAccessDate))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		if (tourOperator == null) {
			if (other.tourOperator != null)
				return false;
		} else if (!tourOperator.equals(other.tourOperator))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}