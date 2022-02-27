package uturismu.unit;

import org.junit.Test;

import uturismu.ServiceFactory;
import uturismu.dto.Account;
import uturismu.dto.Booker;
import uturismu.service.UserService;

public class AccountTest {

	@Test
	public void prova() {
		UserService service = ServiceFactory.getUserService();
		Booker booker = getBooker();
		Account account = getAccount();
		account.setBooker(booker);
		booker.setAccount(account);
		service.createAccount(account, booker);
		
		account = service.logIn("cicciolina@xxx.com", "password");
		System.out.println(account.getId());
		System.out.println(account.getEmail());
		System.out.println(account.getPassword());
		
	}

	private Booker getBooker() {
		Booker booker = new Booker();
		booker.setTaxCode("SPCSMN");
		booker.setFirstName("Simone");
		booker.setLastName("Spaccarotella");
		return booker;
	}

	private Account getAccount() {
		Account account = new Account();
		account.setEmail("cicciolina@xxx.com");
		account.setPassword("password");
		account.setSalt("salt");
		return account;
	}
	
}
