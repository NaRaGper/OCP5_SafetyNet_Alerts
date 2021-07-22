package com.nathan.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordRepository.getMedicalRecords();
	}

	public void postMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.postMedicalRecord(medicalRecord);
	}

	public void putMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.putMedicalRecord(medicalRecord);
	}

	public void deleteMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.deleteMedicalRecord(medicalRecord);
	}
}
