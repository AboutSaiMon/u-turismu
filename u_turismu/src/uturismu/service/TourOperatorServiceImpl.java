package uturismu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uturismu.dao.AccountDao;
import uturismu.dao.HolidayPackageDao;
import uturismu.dao.TourOperatorDao;
import uturismu.dto.HolidayPackage;

@Service
@Transactional
public class TourOperatorServiceImpl implements TourOperatorService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private TourOperatorDao tourOperatorDao;
	@Autowired
	private HolidayPackageDao holidayPackageDao;

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

	@Override
	public void updateHolidayPackage(HolidayPackage holidayPackage) {
		holidayPackageDao.update(holidayPackage);
	}

}