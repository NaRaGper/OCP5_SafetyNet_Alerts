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

import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.service.MedicalRecordService;

@RequestMapping ("/medicalRecord")
@RestController
public class MedicalRecordController {

	@Autowired	
	MedicalRecordService medicalRecordService;
	
	@GetMapping
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordService.getMedicalRecords();
	}
	
	@PostMapping
	public void postMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		medicalRecordService.postMedicalRecord(medicalRecord);
	}
	
	@PutMapping
	public void putMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		medicalRecordService.putMedicalRecord(medicalRecord);
	}
	
	@DeleteMapping
	public void deleteMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		medicalRecordService.deleteMedicalRecord(medicalRecord);
	}
}
