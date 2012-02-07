package uturismu.functional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hibernate.type.SetType;
import org.junit.BeforeClass;
import org.junit.Test;

import uturismu.HashUtil;
import uturismu.ServiceFactory;
import uturismu.dao.AccountDao;
import uturismu.dto.Account;
import uturismu.dto.City;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.AccountType;
import uturismu.exception.ExceptionMessages;
import uturismu.exception.InvalidCredentialException;
import uturismu.service.TourOperatorManagementService;
import uturismu.service.backup.TourOperatorService;

public class TourOperatorManagementTest {

	private static TourOperatorManagementService touroperatorService;
//	private static TourOperatorService touroperatorService;
	private static City city;
	
	@BeforeClass
	public static void init(){
		
		city = new City();
		city.setName("Cosenza");
		city.setProvince("Cosenza");
		ServiceFactory.getCityService().save(city);
		touroperatorService=ServiceFactory.getTourOperatorManagementService();
	}
	
	@Test
	public void createTourOperatorAccount(){
		String email="tourop@gmail.com";
		String password="password";
		Account account=createAccount(email, password);
		TourOperator tourOperator=createTourOperator(account);
		Long id = touroperatorService.createAccount(account, tourOperator);
		
		
//		try{
//			account = touroperatorService.login(email, password);
//			assertThat(id, is(equalTo(account.getId())));
//		}catch (InvalidCredentialException e) {
//			assertThat(e.getMessage(), is(equalTo(ExceptionMessages.INCORRECT_ACCOUNT)));
//		}
	}
	
	private TourOperator createTourOperator(Account account){
		TourOperator to=new TourOperator();
		to.setHolderName("pippo Inzaghi");
		to.setName("io Sono Il tour");
		to.setVatNumber("1234567890123456");
		to.setAccount(account);
		account.setTourOperator(to);
		return to;
	}
	
	private Account createAccount(String email,String password){
		Account account=new Account();
		account.setActive(true);
		account.setEmail(email);
		String salt=HashUtil.generateSalt();
		account.setSalt(salt);
		String psw=HashUtil.getHash(password, salt);
		account.setPassword(password);
		account.setType(AccountType.TOUR_OPERATOR);
		account.setLastAccessTimestamp(new Date());
		account.setRegistrationTimestamp(new Date());
		return account;
	}
	
	
	
}
