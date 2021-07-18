package com.nathan.safetynetalerts.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public List<Person> getPersons() {
		return personRepository.getAllPersons();
	}

	public String postPerson() {
		return "Post working";
	}

	public String putPerson() {
		return "Put Working";
	}

	public void deletePerson(Person person) {
		personRepository.deletePerson(person);
	}
}
