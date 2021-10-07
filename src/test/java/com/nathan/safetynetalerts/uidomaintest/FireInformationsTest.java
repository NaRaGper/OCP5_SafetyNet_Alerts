package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.FireAndFloodPerson;
import com.nathan.safetynetalerts.uidomain.FireInformations;

public class FireInformationsTest {
	
	private static FireInformations fireInformations = new FireInformations();
	
	private int stationNumber;
	private List<FireAndFloodPerson> persons = new ArrayList<>();
	
	@Test
	public void setStationNumber() {
		stationNumber = 8;
		
		fireInformations.setStationNumber(stationNumber);
		
		assertEquals(stationNumber, fireInformations.getStationNumber());
	}
	
	@Test
	public void setPersons() {
		FireAndFloodPerson person1 = new FireAndFloodPerson();
		FireAndFloodPerson person2 = new FireAndFloodPerson();
		
		persons.add(person1);
		persons.add(person2);
		fireInformations.setPersons(persons);
		
		assertEquals(persons, fireInformations.getPersons());
	}
}
