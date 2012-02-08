package uturismu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uturismu.HashUtil;
import uturismu.dao.AccountDao;
import uturismu.dao.TourOperatorDao;
import uturismu.dto.Account;
import uturismu.dto.TourOperator;
import uturismu.exception.InvalidCredentialException;


@Service
@Transactional
public class TourOperatorManagementServiceImpl implements TourOperatorManagementService{

	@Autowired
	AccountDao accountDao;
	@Autowired
	TourOperatorDao tourOperatorDao;
	
	
	@Override
	public Long createAccount(Account account, TourOperator tourOperator) {
		Long id=accountDao.save(account);
		tourOperatorDao.save(tourOperator);
		return id;
	}

	@Override
	@Transactional(readOnly=true)
	public Account login(String email, String password) throws InvalidCredentialException{
		Account account = accountDao.findByEmail(email);
		if(account == null){
			throw new InvalidCredentialException();
		}
		String tmpPsw=HashUtil.getHash(password, account.getSalt());
		if(!tmpPsw.equals(account.getPassword())){
			throw new InvalidCredentialException();
		}
		return account;
	}
}
