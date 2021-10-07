package com.nathan.safetynetalerts.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.repository.MedicalRecordRepository;
import com.nathan.safetynetalerts.service.MedicalRecordService;

@SpringBootTest
public class MedicalRecordServiceTest {

	@MockBean
	MedicalRecordRepository medicalRecordRepository;

	@Autowired
	MedicalRecordService medicalRecordService;

	private static MedicalRecord medicalRecord = new MedicalRecord();
	private static String firstName;
	private static String lastName;
	private static String birthdate;
	private static ArrayList<String> medications = new ArrayList<>();
	private static ArrayList<String> allergies = new ArrayList<>();
	
	@BeforeAll
	private static void setUpMedicalRecord() {
		firstName = "First";
		lastName = "Last";
		birthdate = "12/31/1990";
		medications.add("Medication 01");
		allergies.add("Allergy 01");
		
		medicalRecord.setFirstName(firstName);
		medicalRecord.setLastName(lastName);
		medicalRecord.setBirthdate(birthdate);
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
	}
	
	@Test
	public void getMedicalRecords() {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		Mockito.when(medicalRecordRepository.getMedicalRecords()).thenReturn(medicalRecords);
		
		List<MedicalRecord> result = medicalRecordService.getMedicalRecords();
		
		assertTrue(result.size() == medicalRecords.size());
	}
	
	@Test
	public void postMedicalRecord() {
		Mockito.when(medicalRecordRepository.postMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		
		MedicalRecord result = medicalRecordService.postMedicalRecord(medicalRecord);
		
		assertNotNull(result);
		assertEquals(firstName, result.getFirstName());
		assertEquals(lastName, result.getLastName());
		assertEquals(birthdate, result.getBirthdate());
		assertEquals(medications, result.getMedications());
		assertEquals(allergies, result.getAllergies());
	}
	
	@Test
	public void putMedicalRecord() {
		Mockito.when(medicalRecordRepository.putMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		
		MedicalRecord result = medicalRecordService.putMedicalRecord(medicalRecord);
		
		assertNotNull(result);
		assertEquals(firstName, result.getFirstName());
		assertEquals(lastName, result.getLastName());
		assertEquals(birthdate, result.getBirthdate());
		assertEquals(medications, result.getMedications());
		assertEquals(allergies, result.getAllergies());
	}
	
	@Test
	public void deleteMedicalRecord() {
		Mockito.when(medicalRecordRepository.deleteMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		
		MedicalRecord result = medicalRecordService.deleteMedicalRecord(medicalRecord);
		
		assertNotNull(result);
		assertEquals(firstName, result.getFirstName());
		assertEquals(lastName, result.getLastName());
		assertEquals(birthdate, result.getBirthdate());
		assertEquals(medications, result.getMedications());
		assertEquals(allergies, result.getAllergies());
	}
	
	@Test
	public void getPersonAge() {
		Person person = new Person();
		int age = 18;
		Mockito.when(medicalRecordRepository.getPersonAge(person)).thenReturn(age);
		
		int result = medicalRecordService.getPersonAge(person);
		
		assertEquals(age, result);
	}
	
	@Test
	public void getMedications() {
		Person person = new Person();
		Mockito.when(medicalRecordRepository.getMedications(person)).thenReturn(medications);
		
		List<String> result = medicalRecordService.getMedications(person);
		
		assertEquals(medications, result);
	}
	
	@Test
	public void getAllergies() {
		Person person = new Person();
		Mockito.when(medicalRecordRepository.getAllergies(person)).thenReturn(allergies);
		
		List<String> result = medicalRecordService.getAllergies(person);
		
		assertEquals(allergies, result);
	}
	
	@Test
	public void getNumberOfAdults_withNoAdults() {
		Person person = new Person();
		Person person2 = new Person();
		List<Person> persons = new ArrayList<>();
		int numberOfAdults = 0;
		persons.add(person);
		persons.add(person2);
		Mockito.when(medicalRecordRepository.isAdult(person)).thenReturn(false);
		Mockito.when(medicalRecordRepository.isAdult(person2)).thenReturn(false);
		
		int result = medicalRecordService.getNumberOfAdults(persons);
		
		assertEquals(numberOfAdults, result);
	}
	
	@Test
	public void getNumberOfAdults_withOneAdult() {
		Person person = new Person();
		Person person2 = new Person();
		List<Person> persons = new ArrayList<>();
		int numberOfAdults = 1;
		persons.add(person);
		persons.add(person2);
		Mockito.when(medicalRecordRepository.isAdult(person)).thenReturn(true);
		Mockito.when(medicalRecordRepository.isAdult(person2)).thenReturn(false);
		
		int result = medicalRecordService.getNumberOfAdults(persons);
		
		assertEquals(numberOfAdults, result);
	}
}
