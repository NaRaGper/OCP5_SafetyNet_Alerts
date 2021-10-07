package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.FireAndFloodPerson;
import com.nathan.safetynetalerts.uidomain.FloodStationsInformations;

public class FloodStationsInformationsTest {
	
	private static FloodStationsInformations floodStationsInformations = new FloodStationsInformations();
	
	private Map<String, List<FireAndFloodPerson>> personsFromAddress = new TreeMap<>();
	private List<FireAndFloodPerson> persons = new ArrayList<>();
	
	@Test
	public void setPersonsFromAddress() {
		FireAndFloodPerson person1 = new FireAndFloodPerson();
		FireAndFloodPerson person2 = new FireAndFloodPerson();
		
		persons.add(person1);
		persons.add(person2);
		personsFromAddress.put("01 Test Address", persons);
		floodStationsInformations.setPersonsFromAddress(personsFromAddress);
		
		assertEquals(personsFromAddress, floodStationsInformations.getPersonsFromAddress());
	}
}
