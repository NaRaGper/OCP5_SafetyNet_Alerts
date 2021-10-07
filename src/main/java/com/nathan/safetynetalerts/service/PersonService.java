package com.nathan.safetynetalerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public List<Person> getPersons() {
		return personRepository.getPersons();
	}

	public Person postPerson(Person person) {
		return personRepository.postPerson(person);
	}

	public Person putPerson(Person person) {
		return personRepository.putPerson(person);
	}

	public Person deletePerson(Person person) {
		return personRepository.deletePerson(person);
	}

	public List<Person> getPersonsFromAddresses(List<String> addresses) {
		return personRepository.getPersonsFromAddresses(addresses);
	}
	
	public List<Person> getPersonsFromAddress(String address) {
		return personRepository.getPersonsFromAddress(address);
	}

	public List<Person> getPersonsFromCity(String city) {
		return personRepository.getPersonsFromCity(city);
	}
}
