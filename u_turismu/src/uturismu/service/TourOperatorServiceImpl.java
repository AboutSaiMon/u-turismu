package uturismu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uturismu.dao.HolidayPackageDao;
import uturismu.dao.TourOperatorDao;
import uturismu.dto.HolidayPackage;

@Service
@Transactional
public class TourOperatorServiceImpl implements TourOperatorService {

	@Autowired
	private HolidayPackageDao holidayPackageDao;

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getAllHolidayPackages(Long id) {
		return holidayPackageDao.findAllByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getPublishedHolidayPackages(Long id) {
		return holidayPackageDao.findAllPublishedByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getDraftHolidayPackages(Long id) {
		return holidayPackageDao.findAllDraftByTourOperator(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<HolidayPackage> getExpiredHolidayPackages(Long id) {
		return holidayPackageDao.findAllExpiredByTourOperator(id);
	}

	@Override
	public void updateHolidayPackage(HolidayPackage holidayPackage) {
		holidayPackageDao.update(holidayPackage);
	}

	@Override
	public void deleteHolidayPackage(HolidayPackage holidayPackage) {
		holidayPackageDao.delete(holidayPackage);
	}

	@Override
	public Long createHolidayPackage(HolidayPackage holidayPackage) {
		return holidayPackageDao.save(holidayPackage);
	}

}