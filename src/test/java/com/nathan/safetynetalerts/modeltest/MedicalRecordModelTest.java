package com.nathan.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.model.MedicalRecord;

public class MedicalRecordModelTest {

	private static MedicalRecord medicalRecord = new MedicalRecord();
	
	@Test
	public void setMedicalRecordFirstName() {
		String firstName = "First";
		
		medicalRecord.setFirstName(firstName);
		
		assertEquals(firstName, medicalRecord.getFirstName());
	}
	
	@Test
	public void setMedicalRecordLastName() {
		String lastName = "Last";
		
		medicalRecord.setLastName(lastName);
		
		assertEquals(lastName, medicalRecord.getLastName());
	}
	
	@Test
	public void setMedicalRecordBirthdate() {
		String birthdate = "12/31/1990";
		
		medicalRecord.setBirthdate(birthdate);
		
		assertEquals(birthdate, medicalRecord.getBirthdate());
	}
	
	@Test
	public void setMedicalRecordMedications() {
		ArrayList<String> medications = new ArrayList<>();
		medications.add("Medication Test 01");
		medications.add("Medication Test 02");
		
		medicalRecord.setMedications(medications);
		
		assertEquals(medications, medicalRecord.getMedications());
	}
	
	@Test
	public void setMedicalRecordAllergies() {
		ArrayList<String> allergies = new ArrayList<>();
		allergies.add("Allergy Test 01");
		allergies.add("Allergy Test 02");
		
		medicalRecord.setAllergies(allergies);
		
		assertEquals(allergies, medicalRecord.getAllergies());
	}
}
