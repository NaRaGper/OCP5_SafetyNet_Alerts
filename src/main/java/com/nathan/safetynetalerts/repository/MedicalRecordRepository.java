package com.nathan.safetynetalerts.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.model.Person;

@Repository
public class MedicalRecordRepository {

	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	public List<MedicalRecord> getMedicalRecords() {

		return Database.getInstance().getData().getMedicalrecords();
	}

	public MedicalRecord postMedicalRecord(MedicalRecord medicalRecordToPost) {

		boolean alreadyExists = getMedicalRecords().stream().anyMatch(
				(medicalRecord) -> medicalRecord.getFirstName().equalsIgnoreCase(medicalRecordToPost.getFirstName())
				&& medicalRecord.getLastName().equalsIgnoreCase(medicalRecordToPost.getLastName()));

		if (alreadyExists == false) {
			getMedicalRecords().add(medicalRecordToPost);
			return medicalRecordToPost;
		}
		return null;
	}

	public MedicalRecord putMedicalRecord(MedicalRecord medicalRecordToPut) {

		List<MedicalRecord> existingMedicalRecord = getMedicalRecords().stream().filter(
				(medicalRecord) -> medicalRecord.getFirstName().equalsIgnoreCase(medicalRecordToPut.getFirstName())
				&& medicalRecord.getLastName().equalsIgnoreCase(medicalRecordToPut.getLastName()))
				.collect(Collectors.toList());

		if (existingMedicalRecord.isEmpty() || existingMedicalRecord.size() >= 2) {
			return null;

		} else {
			getMedicalRecords().remove(existingMedicalRecord.get(0));
			getMedicalRecords().add(medicalRecordToPut);
			return medicalRecordToPut;
		}
	}

	public MedicalRecord deleteMedicalRecord(MedicalRecord medicalRecordToDelete) {

		List<MedicalRecord> existingMedicalRecord = getMedicalRecords().stream().filter(
				(medicalRecord) -> medicalRecord.getFirstName().equalsIgnoreCase(medicalRecordToDelete.getFirstName())
				&& medicalRecord.getLastName().equalsIgnoreCase(medicalRecordToDelete.getLastName()))
				.collect(Collectors.toList());

		if (existingMedicalRecord.isEmpty() || existingMedicalRecord.size() >= 2) {
			return null;

		} else {
			getMedicalRecords().remove(existingMedicalRecord.get(0));
			return medicalRecordToDelete;
		}
	}

	public boolean isAdult(Person person) {
		List<MedicalRecord> medicalRecords = getMedicalRecords().stream()
				.filter(m -> m.getFirstName().equalsIgnoreCase(person.getFirstName())
						&& m.getLastName().equalsIgnoreCase(person.getLastName()))
				.collect(Collectors.toList());

		LocalDate now = LocalDate.now();
		String birthdate = medicalRecords.get(0).getBirthdate();
		LocalDate past = LocalDate.parse(birthdate, dateTimeFormatter);

		long years = ChronoUnit.YEARS.between(past, now);

		return (years > 18) ? true : false;
	}

	public int getPersonAge(Person person) {
		List<MedicalRecord> medicalRecords = getMedicalRecords().stream()
				.filter(m -> m.getFirstName().equalsIgnoreCase(person.getFirstName())
						&& m.getLastName().equalsIgnoreCase(person.getLastName()))
				.collect(Collectors.toList());

		LocalDate now = LocalDate.now();
		String birthdate = medicalRecords.get(0).getBirthdate();
		LocalDate past = LocalDate.parse(birthdate, dateTimeFormatter);

		return (int) ChronoUnit.YEARS.between(past, now);
	}

	public List<String> getMedications(Person person) {
		List<MedicalRecord> medicalRecords = getMedicalRecords().stream()
				.filter(m -> m.getFirstName().equalsIgnoreCase(person.getFirstName())
						&& m.getLastName().equalsIgnoreCase(person.getLastName()))
				.collect(Collectors.toList());
		List<String> medications = new ArrayList<>();
		for (MedicalRecord medicalRecord : medicalRecords) {
			medications.addAll(medicalRecord.getMedications());
		}
		return medications;
	}

	public List<String> getAllergies(Person person) {
		List<MedicalRecord> medicalRecords = getMedicalRecords().stream()
				.filter(m -> m.getFirstName().equalsIgnoreCase(person.getFirstName())
						&& m.getLastName().equalsIgnoreCase(person.getLastName()))
				.collect(Collectors.toList());
		List<String> allergies = new ArrayList<>();
		for (MedicalRecord medicalRecord : medicalRecords) {
			allergies.addAll(medicalRecord.getAllergies());
		}
		return allergies;
	}
}
