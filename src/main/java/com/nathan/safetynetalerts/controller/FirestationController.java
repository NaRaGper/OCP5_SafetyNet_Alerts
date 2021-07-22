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

import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.service.FirestationService;

@RequestMapping ("/firestation")
@RestController
public class FirestationController {

	@Autowired
	FirestationService firestationService;
	
	@GetMapping
	public List<Firestation> getFirestations() {
		return firestationService.getFirestations();
	}
	
	@PostMapping
	public void postFirestation(@Valid @RequestBody Firestation firestation) {
		firestationService.postFirestation(firestation);
	}
	
	@PutMapping
	public void putFirestation(@Valid @RequestBody Firestation firestation) {
		firestationService.putFirestation(firestation);
	}
	
	@DeleteMapping
	public void deleteFirestation(@Valid @RequestBody Firestation firestation) {
		firestationService.deleteFirestation(firestation);
	}
}
