package com.nathan.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.Firestation;

@Repository
public class FirestationRepository {

	public List<Firestation> getFirestations() {
		return Database.getInstance().getData().getFirestations();
	}

	public Firestation postFirestation(Firestation firestationToPost) {
		boolean alreadyExists = getFirestations().stream()
				.anyMatch((firestation) -> firestation.getAddress().equalsIgnoreCase(firestationToPost.getAddress()));
		if (alreadyExists == false) {
			getFirestations().add(firestationToPost);
			return firestationToPost;
		}
		return null;
	}

	public Firestation putFirestation(Firestation firestationToPut) {
		List<Firestation> existingFirestation = existingFirestation(firestationToPut);
		if (existingFirestation.isEmpty() || existingFirestation.size() >= 2) {
			return null;
		} else {
			getFirestations().remove(existingFirestation.get(0));
			getFirestations().add(firestationToPut);
			return firestationToPut;
		}
	}

	public Firestation deleteFirestation(Firestation firestationToDelete) {
		List<Firestation> existingFirestation = existingFirestation(firestationToDelete);
		if (existingFirestation.isEmpty() || existingFirestation.size() >= 2) {
			return null;
		} else {
			getFirestations().remove(existingFirestation.get(0));
			return firestationToDelete;
		}
	}

	public List<String> getAddressesFromStationNumber(int stationNumber) {
		return getFirestations().stream().filter(firestation -> firestation.getStation() == stationNumber)
				.map(firestation -> firestation.getAddress()).collect(Collectors.toList());
	}

	public List<String> getAddressesFromStationNumbers(List<Integer> stationNumbers) {
		List<String> addresses = new ArrayList<>();
		for (int stationNumber : stationNumbers) {
			addresses.addAll(getFirestations().stream()
				.filter(f -> f.getStation() == stationNumber)
				.map(f -> f.getAddress())
				.collect(Collectors.toList()));
		}
		return addresses;
	}
	
	public int getStationNumberFromAddress(String address) {
		List<Integer> stationNumbers = getFirestations().stream().filter(f -> f.getAddress().equalsIgnoreCase(address))
				.map(f -> f.getStation())
				.collect(Collectors.toList());
		return stationNumbers.get(0);
	}
	
	private List<Firestation> existingFirestation(Firestation firestationToSearch) {
		List<Firestation> existingFirestation = getFirestations().stream()
				.filter(firestation -> firestation.getAddress().equalsIgnoreCase(firestationToSearch.getAddress()))
				.collect(Collectors.toList());
		return existingFirestation;
	}
}
