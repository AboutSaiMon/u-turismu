package uturismu.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Login implements UTurismuBean {

	private static final long serialVersionUID = 7082569850670642213L;
	@Email
	@NotEmpty
	private String loginEmail;
	@Size(min = 0, max = 15)
	@NotEmpty
	private String loginPassword;

	public Login() {
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

}
