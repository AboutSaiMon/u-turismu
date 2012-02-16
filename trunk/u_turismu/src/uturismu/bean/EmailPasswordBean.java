package uturismu.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailPasswordBean implements UTurismuBean {

	private static final long serialVersionUID = -8856627016704270678L;
	@NotNull
	private String email;
	@NotNull
	@Size(min = 3, max = 15)
	private String password;

	public EmailPasswordBean() {
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}