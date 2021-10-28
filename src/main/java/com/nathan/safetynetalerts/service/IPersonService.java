package com.nathan.safetynetalerts.service;

import java.util.List;

import com.nathan.safetynetalerts.model.Person;

public interface IPersonService {

	public List<Person> getPersons();

	public Person postPerson(Person person);

	public Person putPerson(Person person);
	
	public Person deletePerson(Person person);

	public List<Person> getPersonsFromAddresses(List<String> addresses);
	
	public List<Person> getPersonsFromAddress(String address);

	public List<Person> getPersonsFromCity(String city);
}
