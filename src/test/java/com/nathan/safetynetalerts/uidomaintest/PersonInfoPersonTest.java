package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.PersonInfoPerson;

public class PersonInfoPersonTest {

	private static PersonInfoPerson person = new PersonInfoPerson();

	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String email;
	private List<String> medications = new ArrayList<>();
	private List<String> allergies = new ArrayList<>();

	@Test
	public void constructor() {
		try {
			new PersonInfoPerson(firstName, lastName, address, age, email, medications, allergies);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void setFirstName() {
		firstName = "First";

		person.setFirstName(firstName);

		assertEquals(firstName, person.getFirstName());
	}

	@Test
	public void setLastName() {
		lastName = "Last";
		
		person.setLastName(lastName);

		assertEquals(lastName, person.getLastName());
	}

	@Test
	public void setAddress() {
		address = "01 Test Address";
		
		person.setAddress(address);
		
		assertEquals(address, person.getAddress());
	}

	@Test
	public void setAge() {
		age = 18;
		
		person.setAge(age);
		
		assertEquals(age, person.getAge());
	}

	@Test
	public void setEmail() {
		email = "test@email.com";
		
		person.setEmail(email);
		
		assertEquals(email, person.getEmail());
	}

	@Test
	public void setMedications() {
		medications.add("Medication Test 01");
		medications.add("Medication Test 02");
		
		person.setMedications(medications);
		
		assertEquals(medications, person.getMedications());
	}

	@Test
	public void setAllergies() {
		allergies.add("Allergy Test 01");
		allergies.add("Allergy Test 02");
		
		person.setAllergies(allergies);
		
		assertEquals(allergies, person.getAllergies());
	}
}
