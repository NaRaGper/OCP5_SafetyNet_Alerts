package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.ChildAlertPerson;

public class ChildAlertPersonTest {
	
	private static ChildAlertPerson person = new ChildAlertPerson();
	
	private String firstName;
	private String lastName;
	private int age;
	
	@Test
	public void constructor() {
		try {
			new ChildAlertPerson(firstName, lastName, age);
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
	public void setAge() {
		age = 18;
		
		person.setAge(age);
		
		assertEquals(age, person.getAge());
	}
}
