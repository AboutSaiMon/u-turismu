package uturismu.service;

import java.util.List;

import uturismu.dto.Account;
import uturismu.dto.HolidayPackage;
import uturismu.dto.TourOperator;
import uturismu.dto.enumtype.Status;
import uturismu.exception.InvalidCredentialException;

public interface TourOperatorManagementService {
	
	// TODO: cancellare createAccount, Login , fanno  parte del + generico User
	public Long createAccount( Account account, TourOperator tourOperator );
	public Account login(String email, String password) throws InvalidCredentialException;
	
	/**
	 * Restituisce un lista contenente tutti gli HolidayPackage (pubblicati,bozze,scaduti)
	 * per un dato {@link TourOperator}
	 * 
	 * @param id : è l'id del {@link TourOperator}
	 * @return una {@link List} di {@link HolidayPackage}  
	 */
	public List<HolidayPackage> findAllHolidayPackages(Long id);
	
	
	/**
	 * Restituisce un lista contenente gli HolidayPackage con {@link Status} PUBLISHED
	 * per un dato {@link TourOperator}
	 * 
	 * @param id è l'id del {@link TourOperator}
	 * @return una {@link List} di {@link HolidayPackage}
	 */
	public List<HolidayPackage> findPublishedHolidayPackages(Long id);
	
	/**
	 * Restituisce un lista contenente gli HolidayPackage con {@link Status} DRAFT
	 * per un dato {@link TourOperator}
	 * 
	 * @param id è l'id del {@link TourOperator}
	 * @return una {@link List} di {@link HolidayPackage}
	 */
	public List<HolidayPackage> findDraftHolidayPackages(Long id);

	/**
	 * Restituisce un lista contenente gli HolidayPackage con {@link Status} EXPIRED
	 * per un dato {@link TourOperator}
	 * 
	 * @param id è l'id del {@link TourOperator}
	 * @return una {@link List} di {@link HolidayPackage}
	 */
	public List<HolidayPackage> findExpiredHolidayPackages(Long id);

}
