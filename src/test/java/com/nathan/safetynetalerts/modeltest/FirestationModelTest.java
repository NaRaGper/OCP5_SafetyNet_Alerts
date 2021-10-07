package com.nathan.safetynetalerts.modeltest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.model.Firestation;

public class FirestationModelTest {
	
	private static Firestation firestation = new Firestation();
	
	@Test
	public void setFirestationAddress() {
		String address = "01 Test Address";
		
		firestation.setAddress(address);
		
		assertEquals(address, firestation.getAddress());
	}
	
	@Test
	public void setFirestationStation() {
		int station = 0;
		
		firestation.setStation(station);
		
		assertEquals(station, firestation.getStation());
	}
}
