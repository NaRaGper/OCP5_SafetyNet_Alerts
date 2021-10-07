package com.nathan.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.UIPerson;

public class UIPersonTest {
	
	private static UIPerson uiPerson = new UIPerson();
	
	@SuppressWarnings("unused")
	private static UIPerson uiPerson2 = new UIPerson("First", "Last", "01 Test Address", "000-111-2222");
	
	@Test
	public void setUIPersonFirstName() {
		String firstName = "First";
		
		uiPerson.setFirstName(firstName);
		
		assertEquals(firstName, uiPerson.getFirstName());
	}

	@Test
	public void setUIPersonLastName() {
		String lastName = "Last";
		
		uiPerson.setLastName(lastName);
		
		assertEquals(lastName, uiPerson.getLastName());
	}

	@Test
	public void setUIPersonAddress() {
		String address = "01 Test Address";
		
		uiPerson.setAddress(address);
		
		assertEquals(address, uiPerson.getAddress());
	}

	@Test
	public void setUIPersonPhone() {
		String phone = "000-111-2222";
		
		uiPerson.setPhone(phone);
		
		assertEquals(phone, uiPerson.getPhone());
	}
}
