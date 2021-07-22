package com.nathan.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.repository.FirestationRepository;

@Service
public class FirestationService {
	
	@Autowired
	FirestationRepository firestationRepository;

	public List<Firestation> getFirestations() {
		return firestationRepository.getFirestations();
	}

	public void postFirestation(Firestation firestation) {
		firestationRepository.postFirestation(firestation);
	}

	public void putFirestation(Firestation firestation) {
		firestationRepository.putFirestation(firestation);
	}

	public void deleteFirestation(Firestation firestation) {
		firestationRepository.deleteFirestation(firestation);
	}

}
