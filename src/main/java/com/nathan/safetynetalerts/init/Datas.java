package com.nathan.safetynetalerts.init;

import java.util.ArrayList;
import java.util.List;

import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.model.Person;

public class Datas {
	
	private List<Person> persons = new ArrayList<>();
	private List<MedicalRecord> medicalrecords = new ArrayList<>();
	private List<Firestation> firestations = new ArrayList<>();
	
	public Datas() {
		
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void setMedicalrecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}

	public List<Firestation> getFirestations() {
		return firestations;
	}

	public void setFirestations(List<Firestation> firestations) {
		this.firestations = firestations;
	}
	
}
