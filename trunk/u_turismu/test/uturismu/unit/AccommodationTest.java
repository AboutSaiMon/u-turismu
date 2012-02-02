/*
 * This file is part of "U Turismu" project. 
 * 
 * U Turismu is an enterprise application in support of calabrian tour operators.
 * This system aims to promote tourist services provided by the operators
 * and to develop and improve tourism in Calabria.
 *
 * Copyright (C) 2012 "LagrecaSpaccarotella" team.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uturismu.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import uturismu.BaseTest;
import uturismu.dto.Accommodation;
import uturismu.dto.util.AccommodationType;
import uturismu.service.AccommodationService;

/**
 * @author "LagrecaSpaccarotella" team.
 * 
 */
public class AccommodationTest extends BaseTest {

	private static AccommodationService service;

	@BeforeClass
	public static void initService() {
		service = context.getBean(AccommodationService.class);
	}

	@Test
	public void checkSave() {
		// create an Accommodation
		String vatNumber = "0123456";
		String name = "Springfield Inn.";
		Accommodation accommodation1 = new Accommodation();
		accommodation1.setVatNumber(vatNumber);
		accommodation1.setName(name);
		accommodation1.setType(AccommodationType.HOTEL);
		// save it to the DB
		service.save(accommodation1);
		// retrieve an object with the same vatNumber
		Accommodation accommodation2 = service.findByVatNumber(vatNumber);
		// assert that the two objects are the same
		assertThat(accommodation2.getId(), is(equalTo(accommodation1.getId())));
		// assert that there is only one tuple in the DB
		assertThat(service.rowCount(), is(equalTo(1L)));
	}

	@Test
	public void checkDelete() {
		// retrieve the row count
		Long rowCount = service.rowCount();
		// insert a new accommodation
		Accommodation accommodation = new Accommodation();
		accommodation.setVatNumber("6610");
		accommodation.setName("Nuclear Power B&B");
		accommodation.setType(AccommodationType.BED_AND_BREAKFAST);
		// get the id
		Long id = service.save(accommodation);
		// retrieve the accommodation by its id
		accommodation = service.findById(id);
		// delete the accommodation
		service.delete(accommodation);
		// assert that the row count didn't change, so the accommodation
		// was deleted
		assertThat(service.rowCount(), is(equalTo(rowCount)));
	}
	
	@Test
	public void checkUpdate() {
		// inserts a new accommodation
		Accommodation accommodation = new Accommodation();
		accommodation.setVatNumber(":-)");
		accommodation.setName("Shelbyville Youth");
		accommodation.setType(AccommodationType.HOSTEL);
		service.save(accommodation);
		// changes the name in a new name
		String newName = "Springfield Youth";
		accommodation.setName(newName);
		// updates the column
		service.update(accommodation);
		// retrieves the accommodation
		accommodation = service.findByVatNumber(":-)");
		// checks whether the name was updated
		assertThat(accommodation.getName(), is(equalTo(newName)));
		
	}

	/*
	 * The method should throws an exception because there is a unique constraint
	 * on the "vat_number" attribute.
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void checkUniqueConstraintValidation() {
		// insert an accommodation
		Accommodation a1 = new Accommodation();
		a1.setVatNumber("007");
		a1.setName("Moe's");
		service.save(a1);
		// insert another accommodation with the same vat number
		Accommodation a2 = new Accommodation();
		a2.setVatNumber("007");
		a1.setName("Homer Motel");
		service.save(a2);
	}

}
