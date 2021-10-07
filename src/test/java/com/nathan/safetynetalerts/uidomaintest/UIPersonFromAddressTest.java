package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.UIPerson;
import com.nathan.safetynetalerts.uidomain.UIPersonFromAddress;

public class UIPersonFromAddressTest {
	
	private static UIPersonFromAddress uiPersonFromAddress = new UIPersonFromAddress();
	
	private List<UIPerson> persons = new ArrayList<>();
	private int nombreAdultes;
	private int nombreEnfants;
	
	@Test
	public void setPersons() {
		UIPerson person1 = new UIPerson();
		UIPerson person2 = new UIPerson();
		
		persons.add(person1);
		persons.add(person2);
		uiPersonFromAddress.setPersons(persons);
		
		assertEquals(persons, uiPersonFromAddress.getPersons());
	}
	
	@Test
	public void setNombreAdultes() {
		nombreAdultes = 8;
		
		uiPersonFromAddress.setNombreAdultes(nombreAdultes);
		
		assertEquals(nombreAdultes, uiPersonFromAddress.getNombreAdultes());
	}
	
	@Test
	public void setNombreEnfants() {
		nombreEnfants = 12;
		
		uiPersonFromAddress.setNombreEnfants(nombreEnfants);
		
		assertEquals(nombreEnfants, uiPersonFromAddress.getNombreEnfants());
	}
}
