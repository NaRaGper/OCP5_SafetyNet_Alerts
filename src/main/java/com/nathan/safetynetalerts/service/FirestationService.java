package com.nathan.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.repository.FirestationRepository;

@Service
public class FirestationService implements IFirestationService {
	
	@Autowired
	FirestationRepository firestationRepository;

	public List<Firestation> getFirestations() {
		return firestationRepository.getFirestations();
	}

	public Firestation postFirestation(Firestation firestation) {
		return firestationRepository.postFirestation(firestation);
	}

	public Firestation putFirestation(Firestation firestation) {
		return firestationRepository.putFirestation(firestation);
	}

	public Firestation deleteFirestation(Firestation firestation) {
		return firestationRepository.deleteFirestation(firestation);
	}
	
	public List<String> getAddressesFromStationNumber(int stationNumber) {
		return firestationRepository.getAddressesFromStationNumber(stationNumber);
	}
	
	public List<String> getAddressesFromStationNumbers(List<Integer> stationNumbers) {
		return firestationRepository.getAddressesFromStationNumbers(stationNumbers);
	}
	
	public int getStationNumberFromAddress(String address) {
		return firestationRepository.getStationNumberFromAddress(address);
	}
}
