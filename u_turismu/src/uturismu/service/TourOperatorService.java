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
	public List<HolidayPackage> findAllHolidayPackages(Long id);

	/**
	 * Restituisce tutti gli HolidayPackage con {@link Status} PUBLISHED per un
	 * dato {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> findPublishedHolidayPackages(Long id);

	/**
	 * Restituisce tutti gli HolidayPackage con {@link Status} DRAFT per un dato
	 * {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> findDraftHolidayPackages(Long id);

	/**
	 * Restituisce tutti gli HolidayPackage con {@link Status} EXPIRED per un
	 * dato {@link TourOperator}.
	 * 
	 * @param id
	 *           l'id del tour operator
	 * @return {@link List}<{@link HolidayPackage}>
	 */
	public List<HolidayPackage> findExpiredHolidayPackages(Long id);

	/**
	 * Aggiorna un {@link HolidayPackage} se il suo {@link Status} è DRAFT.
	 * 
	 * @param holidayPackage
	 *           l'oggetto di tipo {@link HolidayPackage} da aggiornare
	 */
	public void updateHolidayPackage(HolidayPackage holidayPackage);

	public void deleteHolidayPackage(HolidayPackage holidayPackage);
	
	public Long addHolidayPackage(HolidayPackage holidayPackage);
	
	
}
