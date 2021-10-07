package com.nathan.safetynetalerts.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.safetynetalerts.error.RequestError;
import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.FirestationService;
import com.nathan.safetynetalerts.service.MedicalRecordService;
import com.nathan.safetynetalerts.service.PersonService;
import com.nathan.safetynetalerts.uidomain.UIPersonFromAddress;
import com.nathan.safetynetalerts.uidomain.UIPerson;

@RequestMapping ("/firestation")
@RestController
public class FirestationController {

	@Autowired
	FirestationService firestationService;

	@Autowired
	PersonService personService;

	@Autowired
	MedicalRecordService medicalRecordService;

	@GetMapping ("/list")
	public List<Firestation> getFirestations() {
		return firestationService.getFirestations();
	}

	@PostMapping
	public ResponseEntity<Object> postFirestation(@Valid @RequestBody Firestation firestation) {

		Firestation result = firestationService.postFirestation(firestation);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(new RequestError("La caserne existe déjà",
					"Une caserne existe déjà à cette adresse"),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping
	public ResponseEntity<Object> putFirestation(@Valid @RequestBody Firestation firestation) {

		Firestation result = firestationService.putFirestation(firestation);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new RequestError("La caserne n'existe pas",
					"Aucune caserne n'existe à cette adresse"),
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteFirestation(@Valid @RequestBody Firestation firestation) {

		Firestation result = firestationService.deleteFirestation(firestation);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new RequestError("La caserne n'existe pas",
					"Aucune caserne n'existe à cette adresse"),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<Object> getPersonsFromStation(@RequestParam String stationNumber) {

		UIPersonFromAddress uipersonFromAddress = new UIPersonFromAddress();
		List<String> addresses = firestationService.getAddressesFromStationNumber(Integer.valueOf(stationNumber));
		List<Person> persons = personService.getPersonsFromAddresses(addresses);
		List<UIPerson> uiPersons = persons.stream().map(p -> UIPerson.toUIPerson(p)).collect(Collectors.toList());
		uipersonFromAddress.setPersons(uiPersons);
		int numberOfAdults = medicalRecordService.getNumberOfAdults(persons);
		uipersonFromAddress.setNombreAdultes(numberOfAdults);
		uipersonFromAddress.setNombreEnfants(persons.size() - numberOfAdults);
		return new ResponseEntity<>(uipersonFromAddress, HttpStatus.OK);
	}
}
