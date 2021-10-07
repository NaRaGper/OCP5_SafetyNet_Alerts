package com.nathan.safetynetalerts.uidomain;

import com.nathan.safetynetalerts.model.Person;

public class ChildAlertPerson {

	private String firstName;
	private String lastName;
	private int age;

	public ChildAlertPerson() {
		super();
	}

	public ChildAlertPerson(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public static ChildAlertPerson toChildAlertPerson(Person person, int age) {
		return new ChildAlertPerson(person.getFirstName(), person.getLastName(), age);
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
