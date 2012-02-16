package uturismu.bean;

import uturismu.dto.enumtype.AccountType;

public abstract class AccountBean implements UTurismuBean {

	private static final long serialVersionUID = 4056061660770229368L;
	private Long userId;
	private String email;
	private AccountType type;

	public AccountBean() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

}
