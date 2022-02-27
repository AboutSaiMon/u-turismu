package uturismu.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Signup implements UTurismuBean {

	private static final long serialVersionUID = -4999212955785903360L;
	@Email
	private String signupEmail;
	@Size(min = 3, max = 15)
	private String signupPassword;

	public Signup() {
	}

	public String getSignupEmail() {
		return signupEmail;
	}

	public void setSignupEmail(String signupEmail) {
		this.signupEmail = signupEmail;
	}

	public String getSignupPassword() {
		return signupPassword;
	}

	public void setSignupPassword(String signupPassword) {
		this.signupPassword = signupPassword;
	}

}
