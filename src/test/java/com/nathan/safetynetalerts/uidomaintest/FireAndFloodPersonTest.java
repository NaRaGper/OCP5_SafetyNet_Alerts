package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.FireAndFloodPerson;

public class FireAndFloodPersonTest {
	
	private static FireAndFloodPerson person = new FireAndFloodPerson();
	
	private String lastName;
	private String firstName;
	private String phone;
	private int age;
	private List<String> medications = new ArrayList<>();
	private List<String> allergies = new ArrayList<>();
	
	@Test
	public void constructor() {
		try {
			new FireAndFloodPerson(lastName, firstName, phone, age, medications, allergies);
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
	public void setPhone() {
		phone = "000-111-2222";
		
		person.setPhone(phone);
		
		assertEquals(phone, person.getPhone());
	}

	@Test
	public void setAge() {
		age = 18;
		
		person.setAge(age);
		
		assertEquals(age, person.getAge());
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
