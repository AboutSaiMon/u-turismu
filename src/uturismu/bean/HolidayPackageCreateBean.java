
package uturismu.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import uturismu.dto.enumtype.Status;

public class HolidayPackageCreateBean implements UTurismuBean {

	private static final long serialVersionUID = -8261826271514741361L;
	@NotEmpty
	@Size(max=40)
	private String name;
	@NotEmpty
	@Size(max=230)
	private String description;
	
//	@NotEmpty
	private Integer customerNumber;
//	@NotEmpty
	private Integer availability;
	// tiene il conto di quanti pacchetti sono stati venduti
	
	private Integer day;
	private Integer month;
	private Integer year;

	private String status;
	
	public HolidayPackageCreateBean() {
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public Integer getAvailability() {
		return availability;
	}

	public Integer getDay() {
		return day;
	}

	public Integer getMonth() {
		return month;
	}

	public Integer getYear() {
		return year;
	}

	public String getStatus() {
		return status;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public void setDay(Integer may) {
		this.day = may;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}