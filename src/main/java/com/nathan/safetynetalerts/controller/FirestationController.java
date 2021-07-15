package com.nathan.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.safetynetalerts.service.FirestationService;

@RequestMapping ("/firestation")
@RestController
public class FirestationController {

	@Autowired
	FirestationService firestationService;
	
	@GetMapping
	public String getFirestation() {
		return firestationService.getFirestation();
	}
	
	@PostMapping
	public String postFirestation() {
		return firestationService.postFirestation();
	}
	
	@PutMapping
	public String putFirestation() {
		return firestationService.putFirestation();
	}
	
	@DeleteMapping
	public String deleteFirestation() {
		return firestationService.deleteFirestation();
	}
}
