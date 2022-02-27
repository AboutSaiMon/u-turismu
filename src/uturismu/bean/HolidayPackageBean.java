package uturismu.bean;

import java.util.Date;

import uturismu.dto.enumtype.Status;

public class HolidayPackageBean implements UTurismuBean {

	private static final long serialVersionUID = -8261826271514741361L;
	private Long id;
	private String name;
	private String description;
	private Integer customerNumber;
	// il numero di pacchetti vacanza messi a disposizione
	private Integer availability;
	// tiene il conto di quanti pacchetti sono stati venduti
	private Integer counter;
	private Date dueDate;
	private Status status;
	private String tourOperatorName;
	private String tourOperatorHolderName;
	private String tourOperatorEmail;

	public HolidayPackageBean() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTourOperatorName() {
		return tourOperatorName;
	}

	public void setTourOperatorName(String tourOperatorName) {
		this.tourOperatorName = tourOperatorName;
	}

	public String getTourOperatorHolderName() {
		return tourOperatorHolderName;
	}

	public void setTourOperatorHolderName(String tourOperatorHolderName) {
		this.tourOperatorHolderName = tourOperatorHolderName;
	}

	public String getTourOperatorEmail() {
		return tourOperatorEmail;
	}

	public void setTourOperatorEmail(String tourOperatorEmail) {
		this.tourOperatorEmail = tourOperatorEmail;
	}

}