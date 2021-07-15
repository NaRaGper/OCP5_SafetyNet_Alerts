package com.nathan.safetynetalerts.service;

import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {
	
	public String getMedicalRecord() {
		return "Get working";
	}

	public String postMedicalRecord() {
		return "Post working";
	}

	public String putMedicalRecord() {
		return "Put Working";
	}

	public String deleteMedicalRecord() {
		return "Delete working";
	}
}
