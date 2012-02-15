package uturismu.bean;

public class AccountRegistration {

	private String email;
	private String password;
	private boolean tourOperator;
	private boolean booker;

	public boolean isTourOperator() {
		return tourOperator;
	}

	public void setTourOperator(boolean tourOperator) {
		this.tourOperator = tourOperator;
	}

	public boolean isBooker() {
		return booker;
	}

	public void setBooker(boolean booker) {
		this.booker = booker;
	}

	public AccountRegistration() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
