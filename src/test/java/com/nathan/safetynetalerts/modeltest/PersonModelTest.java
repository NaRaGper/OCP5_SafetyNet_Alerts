package com.nathan.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.model.Person;


public class PersonModelTest  {
	
	private static Person person = new Person();
	
	@Test
	public void setPersonFirstName() {
		String firstName = "First";
		
		person.setFirstName(firstName);
		
		assertEquals(firstName, person.getFirstName());
	}
	
	@Test
	public void setPersonLastName() {
		String lastName = "Last";
		
		person.setLastName(lastName);
		
		assertEquals(lastName, person.getLastName());
	}

	@Test
	public void setPersonAddress() {
		String address = "01 Test Address";
		
		person.setAddress(address);
		
		assertEquals(address, person.getAddress());
	}
	
	@Test
	public void setPersonCity() {
		String city = "City";
		
		person.setCity(city);
		
		assertEquals(city, person.getCity());
	}
	
	@Test
	public void setPersonZip() {
		String zip = "01234";
		
		person.setZip(zip);
		
		assertEquals(zip, person.getZip());
	}
	
	@Test
	public void setPersonPhone() {
		String phone = "000-111-2222";
		
		person.setPhone(phone);
		
		assertEquals(phone, person.getPhone());
	}
	
	@Test
	public void setPersonEmail() {
		String email = "test@email.com";
		
		person.setEmail(email);
		
		assertEquals(email, person.getEmail());
	}
}
