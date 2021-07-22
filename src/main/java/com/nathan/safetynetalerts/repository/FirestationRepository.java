package com.nathan.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.Firestation;

@Repository
public class FirestationRepository {

	public List<Firestation> getFirestations() {

		return Database.getInstance().getData().getFirestations();
	}

	public void postFirestation(Firestation firestationToPost) {

		List<Firestation> firestations = getFirestations();
		boolean alreadyExists = false;

		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equalsIgnoreCase(firestationToPost.getAddress())) {

				alreadyExists = true;
				break;
			}
		}

		if (alreadyExists == false) { 
			firestations.add(firestationToPost); 
		}

	}

	public void putFirestation(Firestation firestationToPut) {

		List<Firestation> firestations = getFirestations();

		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equalsIgnoreCase(firestationToPut.getAddress())) {

				firestations.remove(firestation);
				firestations.add(firestationToPut);
				break;
			}
		}
	}

	public void deleteFirestation(Firestation firestationToDelete) {

		List<Firestation> firestations = getFirestations();

		for (Firestation firestation : firestations) {
			if (firestation.getAddress().equalsIgnoreCase(firestationToDelete.getAddress())) {

				firestations.remove(firestation);
				break;
			}
		}
	}
}
