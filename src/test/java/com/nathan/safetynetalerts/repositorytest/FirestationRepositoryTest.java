package com.nathan.safetynetalerts.repositorytest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.repository.FirestationRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FirestationRepositoryTest {

	@Autowired
	FirestationRepository firestationRepository;
	
	private static Database database = Database.getInstance();
	
	private Firestation firestation = new Firestation();
	private String address;
	private int station;
	
	@BeforeEach
	private void setUp() {
		address = "01 Test Address";
		station = 8;
		
		firestation.setAddress(address);
		firestation.setStation(station);
	}
	
	@AfterEach
	public void cleanUp() {
		if (firestationRepository.getFirestations().contains(firestation)) {
			firestationRepository.deleteFirestation(firestation);
		}
	}
	
	@Test
	public void getFirestations() {
		try (MockedStatic<Database> mock = Mockito.mockStatic(Database.class)) {
			mock.when(Database::getInstance).thenReturn(database);

			List<Firestation> firestations = firestationRepository.getFirestations();

			assertTrue(!firestations.isEmpty());
		}
	}
	
	@Test
	public void postFirestation_whenFirestationDoesntExist_shouldReturnFirestation() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			Firestation result = firestationRepository.postFirestation(firestation);
			List<Firestation> firestations = firestationRepository.getFirestations();

			assertNotNull(result);
			assertTrue(firestations.contains(result));
		}
	}
	
	@Test
	public void postFirestation_whenFirestationExists_shouldReturnNull() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			firestationRepository.postFirestation(firestation);
			Firestation result = firestationRepository.postFirestation(firestation);
			List<Firestation> firestations = firestationRepository.getFirestations();

			assertNull(result);
			assertTrue(!firestations.contains(result));
		}
	}
	
	@Test
	public void putFirestation_whenFirestationDoesntExist_shouldReturnNull() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			Firestation result = firestationRepository.putFirestation(firestation);
			List<Firestation> firestations = firestationRepository.getFirestations();

			assertNull(result);
			assertTrue(!firestations.contains(result));
		}
	}
	
	@Test
	public void putFirestation_whenFirestationExists_shouldReturnModifiedFirestation() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			firestation = firestationRepository.postFirestation(firestation);
			firestation.setStation(12);
			
			Firestation result = firestationRepository.putFirestation(firestation);

			assertNotNull(result);
			assertTrue(result.getStation() == 12);
		}
	}
	
	@Test
	public void deleteFirestation_whenFirestationDoesntExist_shouldReturnNull() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			Firestation result = firestationRepository.deleteFirestation(firestation);
			List<Firestation> firestations = firestationRepository.getFirestations();

			assertNull(result);
			assertTrue(!firestations.contains(result));
		}
	}
	
	@Test
	public void deleteFirestation_whenFirestationExists_shouldReturnDeletedFirestation() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			
			firestationRepository.postFirestation(firestation);
			Firestation result = firestationRepository.deleteFirestation(firestation);
			List<Firestation> firestations = firestationRepository.getFirestations();

			assertNotNull(result);
			assertTrue(!firestations.contains(result));
		}
	}
}
