package com.nathan.safetynetalerts.uidomain;

import com.nathan.safetynetalerts.model.Person;

public class UIPerson {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	public UIPerson() {
		super();
	}

	public UIPerson(String firstName, String lastName, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	public static UIPerson toUIPerson(Person person) {
		return new UIPerson(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


}

