package com.nathan.safetynetalerts.uidomain;

import java.util.ArrayList;
import java.util.List;

public class FireInformations {
	
	private int stationNumber;
	private List<FireAndFloodPerson> persons = new ArrayList<>();
	
	public int getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	public List<FireAndFloodPerson> getPersons() {
		return persons;
	}
	public void setPersons(List<FireAndFloodPerson> persons) {
		this.persons = persons;
	}
	
	
}
