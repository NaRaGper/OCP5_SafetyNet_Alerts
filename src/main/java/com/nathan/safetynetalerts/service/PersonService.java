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

	public void deletePerson(Person person) {
		personRepository.deletePerson(person);
	}
}
