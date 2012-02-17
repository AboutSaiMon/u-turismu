package uturismu.exception;

public class AccountException extends RuntimeException {

	private static final long serialVersionUID = -5551859264059836824L;
	private String message;

	public AccountException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
