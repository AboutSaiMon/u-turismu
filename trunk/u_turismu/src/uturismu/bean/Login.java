package uturismu.bean;

import javax.validation.constraints.Size;

public class Login implements UTurismuBean {

	private static final long serialVersionUID = 7082569850670642213L;
	@Size(min = 6, max = 40)
	private String loginEmail;
	@Size(min = 3, max = 15)
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
