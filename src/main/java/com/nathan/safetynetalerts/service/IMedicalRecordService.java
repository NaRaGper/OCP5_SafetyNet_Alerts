package com.nathan.safetynetalerts.service;

import java.util.List;

import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.model.Person;

public interface IMedicalRecordService {
	
	public List<MedicalRecord> getMedicalRecords();

	public MedicalRecord postMedicalRecord(MedicalRecord medicalRecord);
	
	public MedicalRecord putMedicalRecord(MedicalRecord medicalRecord);

	public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord);

	public int getNumberOfAdults(List<Person> persons);
	
	public int getPersonAge(Person person);
	
	public List<String> getMedications(Person person);
	
	public List<String> getAllergies(Person person);
}
