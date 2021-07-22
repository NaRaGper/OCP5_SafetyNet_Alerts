package com.nathan.safetynetalerts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.safetynetalerts.error.RequestError;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.PersonService;

@RequestMapping ("/person")
@RestController
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping
	public List<Person> getPersons() {
		return personService.getPersons();
	}
	
	@PostMapping
	public ResponseEntity<Object> postPerson(@Valid @RequestBody Person person) {
		Person result = personService.postPerson(person);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.CREATED);
			
		} else {
			return new ResponseEntity<>(new RequestError("La personne existe déjà", 
					"Il existe déjà une personne avec ce nom et prénom"), 
					HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PutMapping
	public void putPerson(@Valid @RequestBody Person person) {
		Person result = personService.putPerson(person);
		if (result != null) {
			
		} else {
			
		}
	}
	
	@DeleteMapping
	public void deletePerson(@Valid @RequestBody Person person) {
		personService.deletePerson(person);
	}
}