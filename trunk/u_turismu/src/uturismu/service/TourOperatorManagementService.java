package uturismu.service;

import uturismu.dto.Account;
import uturismu.dto.TourOperator;
import uturismu.exception.InvalidCredentialException;

public interface TourOperatorManagementService {
	
	public Long createAccount( Account account, TourOperator tourOperator );
	public Account login(String email, String password) throws InvalidCredentialException;

}
