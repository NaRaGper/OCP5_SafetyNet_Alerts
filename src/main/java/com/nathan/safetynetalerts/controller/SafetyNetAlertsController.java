package com.nathan.safetynetalerts.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.FirestationService;
import com.nathan.safetynetalerts.service.MedicalRecordService;
import com.nathan.safetynetalerts.service.PersonService;
import com.nathan.safetynetalerts.uidomain.ChildAlertPerson;
import com.nathan.safetynetalerts.uidomain.ChildAlertPersonFromAddress;
import com.nathan.safetynetalerts.uidomain.FireAndFloodPerson;
import com.nathan.safetynetalerts.uidomain.FireInformations;
import com.nathan.safetynetalerts.uidomain.FloodStationsInformations;
import com.nathan.safetynetalerts.uidomain.PersonInfoPerson;
import com.nathan.safetynetalerts.uidomain.PersonInformations;

@RestController
public class SafetyNetAlertsController {

	@Autowired
	FirestationService firestationService;

	@Autowired
	PersonService personService;

	@Autowired
	MedicalRecordService medicalRecordService;

	@GetMapping("/childAlert")
	public ResponseEntity<Object> getChildAlert(@RequestParam String address) {
		ChildAlertPersonFromAddress childAlertPersonFromAddress = new ChildAlertPersonFromAddress();
		List<ChildAlertPerson> persons = personService.getPersonsFromAddress(address).stream()
				.map(p -> ChildAlertPerson.toChildAlertPerson(p, medicalRecordService.getPersonAge(p)))
				.collect(Collectors.toList());
		List<ChildAlertPerson> children = persons.stream().filter(p -> p.getAge() <= 18).collect(Collectors.toList());
		if (children.isEmpty()) {
			return new ResponseEntity<>("", HttpStatus.OK);
		}
		childAlertPersonFromAddress.setChildren(children);
		childAlertPersonFromAddress
				.setAdults(persons.stream().filter(p -> p.getAge() > 18).collect(Collectors.toList()));
		return new ResponseEntity<>(childAlertPersonFromAddress, HttpStatus.OK);
	}

	@GetMapping("/phoneAlert")
	public ResponseEntity<Object> getPhoneAlert(@RequestParam int firestation) {
		List<String> addresses = firestationService.getAddressesFromStationNumber(Integer.valueOf(firestation));
		List<Person> persons = personService.getPersonsFromAddresses(addresses);
		Set<String> phoneNumbers = new TreeSet<>();
		for (Person person : persons) {
			phoneNumbers.add(person.getPhone());
		}
		return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
	}

	@GetMapping("/fire")
	public ResponseEntity<Object> getFire(@RequestParam String address) {
		FireInformations fireInformations = new FireInformations();
		List<FireAndFloodPerson> persons = personService.getPersonsFromAddress(address).stream()
				.map(p -> new FireAndFloodPerson(p.getLastName(), p.getFirstName(), p.getPhone(),
						medicalRecordService.getPersonAge(p), medicalRecordService.getMedications(p),
						medicalRecordService.getAllergies(p)))
				.collect(Collectors.toList());
		fireInformations.setStationNumber(firestationService.getStationNumberFromAddress(address));
		fireInformations.setPersons(persons);
		return new ResponseEntity<>(fireInformations, HttpStatus.OK);
	}

	@GetMapping("/flood/stations")
	public ResponseEntity<Object> getFloodStations(@RequestParam List<Integer> stations) {
		FloodStationsInformations floodStationsInformations = new FloodStationsInformations();
		List<String> addresses = firestationService.getAddressesFromStationNumbers(stations);
		Map<String, List<FireAndFloodPerson>> personsFromAddress = new TreeMap<>();
		for (String address : addresses) {
			personsFromAddress.put(address,
					personService.getPersonsFromAddress(address).stream()
							.map(p -> new FireAndFloodPerson(p.getLastName(), p.getFirstName(), p.getPhone(),
									medicalRecordService.getPersonAge(p), medicalRecordService.getMedications(p),
									medicalRecordService.getAllergies(p)))
							.collect(Collectors.toList()));
		}
		floodStationsInformations.setPersonsFromAddress(personsFromAddress);
		return new ResponseEntity<>(floodStationsInformations, HttpStatus.OK);
	}

	@GetMapping("/personInfo")
	public ResponseEntity<Object> getPersonInfo(@RequestParam String firstName, @RequestParam String lastName) {
		PersonInformations personInformations = new PersonInformations();
		List<PersonInfoPerson> sameLastName = personService.getPersons().stream()
				.filter(p -> p.getLastName().equalsIgnoreCase(lastName))
				.map(p -> new PersonInfoPerson(p.getFirstName(), p.getLastName(), p.getAddress(),
						medicalRecordService.getPersonAge(p), p.getEmail(), medicalRecordService.getMedications(p),
						medicalRecordService.getAllergies(p)))
				.collect(Collectors.toList());
		List<PersonInfoPerson> persons = sameLastName.stream().filter(p -> p.getFirstName().equalsIgnoreCase(firstName))
				.collect(Collectors.toList());
		if (persons.isEmpty()) {
			return new ResponseEntity<>("Cette personne n'existe pas", HttpStatus.OK);
		}
		if (!sameLastName.isEmpty()) {
			sameLastName.remove(persons.get(0));
			personInformations.setSameLastName(sameLastName);
		} 
		if (sameLastName.isEmpty()) {
			return new ResponseEntity<>(persons.get(0), HttpStatus.OK);
		} else {
			personInformations.setPerson(persons.get(0));
		}
		return new ResponseEntity<>(personInformations, HttpStatus.OK);
	}

	@GetMapping("/communityEmail")
	public ResponseEntity<Object> getCommunityEmail(@RequestParam String city) {
		Set<String> emails = personService.getPersonsFromCity(city).stream().map(p -> p.getEmail())
				.collect(Collectors.toSet());
		return new ResponseEntity<>(emails, HttpStatus.OK);
	}
}
