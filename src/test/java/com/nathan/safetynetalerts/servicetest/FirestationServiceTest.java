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

import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.repository.FirestationRepository;
import com.nathan.safetynetalerts.service.FirestationService;

@SpringBootTest
public class FirestationServiceTest {
	
	@MockBean
	FirestationRepository firestationRepository;

	@Autowired
	FirestationService firestationService;
	
	private static Firestation firestation = new Firestation();
	private static String address;
	private static int station;
	
	@BeforeAll
	private static void setUpFirestation() {
		address = "01 Test Address";
		station = 8;
		
		firestation.setAddress(address);
		firestation.setStation(station);
	}
	
	@Test
	public void getFirestations() {
		List<Firestation> firestations = new ArrayList<>();
		Mockito.when(firestationRepository.getFirestations()).thenReturn(firestations);

		List<Firestation> result = firestationService.getFirestations();

		assertTrue(result.size() == firestations.size());
	}
	
	@Test
	public void postFirestation() {
		Mockito.when(firestationRepository.postFirestation(firestation)).thenReturn(firestation);
		
		Firestation result = firestationService.postFirestation(firestation);
		
		assertNotNull(result);
		assertEquals(address, result.getAddress());
		assertEquals(station, result.getStation());
	}
	
	@Test
	public void putFirestation() {
		Mockito.when(firestationRepository.putFirestation(firestation)).thenReturn(firestation);
		
		Firestation result = firestationService.putFirestation(firestation);
		
		assertNotNull(result);
		assertEquals(address, result.getAddress());
		assertEquals(station, result.getStation());
	}
	
	@Test
	public void deleteFirestation() {
		Mockito.when(firestationRepository.deleteFirestation(firestation)).thenReturn(firestation);
		
		Firestation result = firestationService.deleteFirestation(firestation);
		
		assertNotNull(result);
		assertEquals(address, result.getAddress());
		assertEquals(station, result.getStation());
	}
	
	@Test
	public void getAddressesFromStationNumber() {
		List<String> addresses = new ArrayList<>();
		addresses.add(address);
		Mockito.when(firestationRepository.getAddressesFromStationNumber(station)).thenReturn(addresses);
		
		List<String> result = firestationService.getAddressesFromStationNumber(station);
		
		assertTrue(result.size() == addresses.size());
	}
	
	@Test
	public void getAddressesFromStationNumbers() {
		List<String> addresses = new ArrayList<>();
		List<Integer> stations = new ArrayList<>();
		addresses.add(address);
		stations.add(station);
		Mockito.when(firestationRepository.getAddressesFromStationNumbers(stations)).thenReturn(addresses);
		
		List<String> result = firestationService.getAddressesFromStationNumbers(stations);
		
		assertTrue(result.size() == addresses.size());
	}
	
	@Test
	public void getStationNumberFromAddress() {
		Mockito.when(firestationRepository.getStationNumberFromAddress(address)).thenReturn(station);
		
		int result = firestationService.getStationNumberFromAddress(address);
		
		assertEquals(result, station);
	}

}
