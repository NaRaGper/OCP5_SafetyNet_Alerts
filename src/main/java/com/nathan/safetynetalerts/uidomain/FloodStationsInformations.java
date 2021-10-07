package com.nathan.safetynetalerts.uidomain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FloodStationsInformations {
	
	private Map<String, List<FireAndFloodPerson>> personsFromAddress = new TreeMap<>();

	public Map<String, List<FireAndFloodPerson>> getPersonsFromAddress() {
		return personsFromAddress;
	}

	public void setPersonsFromAddress(Map<String, List<FireAndFloodPerson>> personsFromAddress) {
		this.personsFromAddress = personsFromAddress;
	}
}
