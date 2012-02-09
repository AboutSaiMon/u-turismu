package uturismu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uturismu.HashUtil;
import uturismu.dao.AccountDao;
import uturismu.dao.HolidayPackageDao;
import uturismu.dao.TourOperatorDao;
import uturismu.dto.Account;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.exception.InvalidCredentialException;

@Service
@Transactional
public class TourOperatorManagementServiceImpl implements TourOperatorManagementService {

	@Autowired
	AccountDao accountDao;
	@Autowired
	TourOperatorDao tourOperatorDao;
	@Autowired
	HolidayPackageDao holidayPackageDao;

	@Override
	public Long createAccount(Account account, TourOperator tourOperator) {
		Long id = accountDao.save(account);
		tourOperatorDao.save(tourOperator);
		return id;
	}

	@Override
	@Transactional(readOnly = true)
	public Account login(String email, String password) throws InvalidCredentialException {
		Account account = accountDao.findByEmail(email);
		if (account == null) {
			throw new InvalidCredentialException();
		}
		String tmpPsw = HashUtil.getHash(password, account.getSalt());
		if (!tmpPsw.equals(account.getPassword())) {
			throw new InvalidCredentialException();
		}
		return account;
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> findAllHolidayPackages(Long id) {
		return holidayPackageDao.findAllByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> findPublishedHolidayPackages(Long id) {
		return holidayPackageDao.findAllPublishedByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> findDraftHolidayPackages(Long id) {
		return holidayPackageDao.findAllDraftByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> findExpiredHolidayPackages(Long id) {
		return holidayPackageDao.findAllExpiredByTourOperator(id);
	}
}