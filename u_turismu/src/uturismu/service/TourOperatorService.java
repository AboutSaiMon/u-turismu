package uturismu.service;

import java.util.List;

import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.Status;

public interface TourOperatorService {

	/**
	 * Restituisce tutti gli HolidayPackage (pubblicati, bozze, scaduti) per un
	 * dato {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> getAllHolidayPackages(Long id);

	/**
	 * Restituisce tutti gli HolidayPackage con {@link Status} PUBLISHED per un
	 * dato {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> getPublishedHolidayPackages(Long id);

	/**
	 * Restituisce tutti gli HolidayPackage con {@link Status} DRAFT per un dato
	 * {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> getDraftHolidayPackages(Long id);

	/**
	 * Restituisce tutti gli HolidayPackage con {@link Status} EXPIRED per un
	 * dato {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> getExpiredHolidayPackages(Long id);

	/**
	 * Aggiorna un {@link HolidayPackage} se il suo {@link Status} è DRAFT.
	 * 
	 * @param holidayPackage
	 *           l'oggetto di tipo {@link HolidayPackage} da aggiornare
	 */
	
	public void updateHolidayPackage(HolidayPackage holidayPackage);

	public void deleteHolidayPackage(HolidayPackage holidayPackage);

	public Long createHolidayPackage(HolidayPackage holidayPackage);

}
