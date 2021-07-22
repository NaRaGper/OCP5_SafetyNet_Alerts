package com.nathan.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepository {

	public List<MedicalRecord> getMedicalRecords() {

		return Database.getInstance().getData().getMedicalrecords();
	}

	public void postMedicalRecord(MedicalRecord medicalRecordToPost) {

		List<MedicalRecord> medicalRecords = getMedicalRecords();
		boolean alreadyExists = false;

		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(medicalRecordToPost.getFirstName()) &&
					medicalRecord.getLastName().equalsIgnoreCase(medicalRecordToPost.getLastName()))
			{
				alreadyExists = true;
				break;
			}
		}

		if (alreadyExists == false) {
			medicalRecords.add(medicalRecordToPost);
		}
	}

	public void putMedicalRecord(MedicalRecord medicalRecordToPut) {
		
		List<MedicalRecord> medicalRecords = getMedicalRecords();
		
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(medicalRecordToPut.getFirstName()) &&
					medicalRecord.getLastName().equalsIgnoreCase(medicalRecordToPut.getLastName()))
			{
				medicalRecords.remove(medicalRecord);
				medicalRecords.add(medicalRecordToPut);
				break;
			}
		}
	}

	public void deleteMedicalRecord(MedicalRecord medicalRecordToDelete) {
		
		List<MedicalRecord> medicalRecords = getMedicalRecords();
		
		for (MedicalRecord medicalRecord : medicalRecords) {
			if (medicalRecord.getFirstName().equalsIgnoreCase(medicalRecordToDelete.getFirstName()) &&
					medicalRecord.getLastName().equalsIgnoreCase(medicalRecordToDelete.getLastName()))
			{
				medicalRecords.remove(medicalRecord);
				break;
			}
		}
	}
}
