package uturismu.bean;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Credential {

	@NotEmpty
	@Size(min = 1, max = 20)
	private String email;
	@NotEmpty
	@Size(min = 3, max = 12)
	private String password;
	
	public Credential() {
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
