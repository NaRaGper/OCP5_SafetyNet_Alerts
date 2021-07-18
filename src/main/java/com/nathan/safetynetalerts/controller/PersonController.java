package com.nathan.safetynetalerts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.PersonService;

@RequestMapping ("/person")
@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public List<Person> getPerson() {
		return personService.getPersons();
	}
	
	@PostMapping
	public String postPerson() {
		return personService.postPerson();
	}
	
	@PutMapping
	public String putPerson() {
		return personService.putPerson();
	}
	
	@DeleteMapping
	public void deletePerson(@Valid @RequestBody Person person) {
		personService.deletePerson(person);
	}
}