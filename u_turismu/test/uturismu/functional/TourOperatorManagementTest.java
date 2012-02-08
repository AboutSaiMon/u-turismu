package uturismu.functional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
		
		account=null;
		account = touroperatorService.login(email, password);
		assertThat(account, is(org.hamcrest.Matchers.notNullValue()));
		System.out.println(account.getEmail());			
		assertThat(id, is(equalTo(account.getId())));
	}
	
	@Test(expected = InvalidCredentialException.class)
	public void createTourOperatorWithException(){
		String email="touroperatorException@gmail.com";
		String password="password";
		Account account=createAccount(email, password);
		TourOperator tourOperator=createTourOperatorEX(account);
		Long id = touroperatorService.createAccount(account, tourOperator);
		
		account=null;
		password="passwordSbagliata";
		account = touroperatorService.login(email, password);
	}
	
	
	private TourOperator createTourOperator(Account account){
		TourOperator to=new TourOperator();
		to.setHolderName("pippo Inzaghi");
		to.setName("io Sono Il tour");
		to.setVatNumber("12345678910");
		to.setAccount(account);
		account.setTourOperator(to);
		return to;
	}
	
	private TourOperator createTourOperatorEX(Account account){
		TourOperator to=new TourOperator();
		to.setHolderName("Eccezzione");
		to.setName("io Sono un eccezzione");
		to.setVatNumber("11111111111");
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
		account.setPassword(psw);
		account.setType(AccountType.TOUR_OPERATOR);
		account.setLastAccessTimestamp(new Date());
		account.setRegistrationTimestamp(new Date());
		return account;
	}
	
	
	
}
