package com.nathan.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nathan.safetynetalerts.service.MedicalRecordService;

@RequestMapping ("/medicalRecord")
@RestController
public class MedicalRecordController {

	@Autowired	
	MedicalRecordService medicalRecordService;
	
	@GetMapping
	public String getMedicalRecord() {
		return medicalRecordService.getMedicalRecord();
	}
	
	@PostMapping
	public String postMedicalRecord() {
		return medicalRecordService.postMedicalRecord();
	}
	
	@PutMapping
	public String putMedicalRecord() {
		return medicalRecordService.putMedicalRecord();
	}
	
	@DeleteMapping
	public String deleteMedicalRecord() {
		return medicalRecordService.deleteMedicalRecord();
	}
}
