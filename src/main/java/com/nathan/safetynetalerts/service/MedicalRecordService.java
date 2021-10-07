package com.nathan.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordRepository.getMedicalRecords();
	}

	public MedicalRecord postMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.postMedicalRecord(medicalRecord);
	}

	public MedicalRecord putMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.putMedicalRecord(medicalRecord);
	}

	public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordRepository.deleteMedicalRecord(medicalRecord);
	}

	public int getNumberOfAdults(List<Person> persons) {
		int count = 0;
		for (Person person : persons) {
			if (medicalRecordRepository.isAdult(person)) {
				count++;
			}
		}
		return count;
	}
	
	public int getPersonAge(Person person) {
		return medicalRecordRepository.getPersonAge(person);
	}
	
	public List<String> getMedications(Person person) {
		return medicalRecordRepository.getMedications(person);
	}
	
	public List<String> getAllergies(Person person) {
		return medicalRecordRepository.getAllergies(person);
	}
}
