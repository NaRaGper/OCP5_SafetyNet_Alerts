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
import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.service.IMedicalRecordService;

@RequestMapping ("/medicalRecord")
@RestController
public class MedicalRecordController {

	@Autowired	
	IMedicalRecordService medicalRecordService;

	@GetMapping
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordService.getMedicalRecords();
	}

	@PostMapping
	public ResponseEntity<Object> postMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {

		MedicalRecord result = medicalRecordService.postMedicalRecord(medicalRecord);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(new RequestError("Le dossier médical existe déjà",
					"Il existe déjà un dossier médical pour cette personne"),
					HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping
	public ResponseEntity<Object> putMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		
		MedicalRecord result = medicalRecordService.putMedicalRecord(medicalRecord);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new RequestError("Le dossier médical n'existe pas",
					"Il n'existe pas de dossier médical pour cette personne"),
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public ResponseEntity<Object> deleteMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
		
		MedicalRecord result = medicalRecordService.deleteMedicalRecord(medicalRecord);

		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new RequestError("Le dossier médical n'existe pas",
					"Il n'existe pas de dossier médical pour cette personne"),
					HttpStatus.NOT_FOUND);
		}
	}
}
