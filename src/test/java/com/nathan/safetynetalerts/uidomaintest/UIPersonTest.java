package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.UIPerson;

public class UIPersonTest {

	private static UIPerson uiPerson = new UIPerson();

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	@Test
	public void constructor() {
		try {
			new UIPerson(firstName, lastName, address, phone);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void setUIPersonFirstName() {
		firstName = "First";

		uiPerson.setFirstName(firstName);

		assertEquals(firstName, uiPerson.getFirstName());
	}

	@Test
	public void setUIPersonLastName() {
		lastName = "Last";

		uiPerson.setLastName(lastName);

		assertEquals(lastName, uiPerson.getLastName());
	}

	@Test
	public void setUIPersonAddress() {
		address = "01 Test Address";

		uiPerson.setAddress(address);

		assertEquals(address, uiPerson.getAddress());
	}

	@Test
	public void setUIPersonPhone() {
		phone = "000-111-2222";

		uiPerson.setPhone(phone);

		assertEquals(phone, uiPerson.getPhone());
	}
}
