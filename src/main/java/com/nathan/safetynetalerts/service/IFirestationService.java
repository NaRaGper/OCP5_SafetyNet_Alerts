package com.nathan.safetynetalerts.service;

import java.util.List;

import com.nathan.safetynetalerts.model.Firestation;

public interface IFirestationService {

	public List<Firestation> getFirestations();

	public Firestation postFirestation(Firestation firestation);

	public Firestation putFirestation(Firestation firestation);

	public Firestation deleteFirestation(Firestation firestation);
	
	public List<String> getAddressesFromStationNumber(int stationNumber);
	
	public List<String> getAddressesFromStationNumbers(List<Integer> stationNumbers);
	
	public int getStationNumberFromAddress(String address);
}
