package com.nathan.safetynetalerts.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

public class MedicalRecord {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	private String birthdate;
	private List<String> medications = new ArrayList<>();
	private List<String> allergies = new ArrayList<>();
	
	public MedicalRecord() {
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(ArrayList<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}
	
	
}
