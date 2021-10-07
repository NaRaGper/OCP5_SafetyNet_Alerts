package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.PersonInfoPerson;
import com.nathan.safetynetalerts.uidomain.PersonInformations;

public class PersonInformationsTest {
	
	PersonInformations personInformations = new PersonInformations();
	
	private PersonInfoPerson person;
	private List<PersonInfoPerson> sameLastName = new ArrayList<>();
	
	@Test
	public void setPerson() {
		person = new PersonInfoPerson();
		
		personInformations.setPerson(person);
		
		assertEquals(person, personInformations.getPerson());
	}
	
	@Test
	public void setSameLastName() {
		PersonInfoPerson person1 = new PersonInfoPerson();
		PersonInfoPerson person2 = new PersonInfoPerson();
		
		sameLastName.add(person1);
		sameLastName.add(person2);
		personInformations.setSameLastName(sameLastName);
		
		assertEquals(sameLastName, personInformations.getSameLastName());
	}
}
