package com.nathan.safetynetalerts.uidomaintest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.nathan.safetynetalerts.uidomain.ChildAlertPerson;
import com.nathan.safetynetalerts.uidomain.ChildAlertPersonFromAddress;

public class ChildAlertPersonFromAddressTest {
	
	ChildAlertPersonFromAddress childAlertPersonFromAddress = new ChildAlertPersonFromAddress();
	
	private List<ChildAlertPerson> children = new ArrayList<>();
	private List<ChildAlertPerson> adults = new ArrayList<>();
	
	@Test
	public void constructor() {
		try {
			new ChildAlertPersonFromAddress(children);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void setChildren() {
		ChildAlertPerson person1 = new ChildAlertPerson();
		ChildAlertPerson person2 = new ChildAlertPerson();
		
		children.add(person1);
		children.add(person2);
		childAlertPersonFromAddress.setChildren(children);
		
		assertEquals(children, childAlertPersonFromAddress.getChildren());
	}
	
	@Test
	public void setAdults() {
		ChildAlertPerson person3 = new ChildAlertPerson();
		ChildAlertPerson person4 = new ChildAlertPerson();
		
		adults.add(person3);
		adults.add(person4);
		childAlertPersonFromAddress.setAdults(adults);
		
		assertEquals(adults, childAlertPersonFromAddress.getAdults());
	}
}
