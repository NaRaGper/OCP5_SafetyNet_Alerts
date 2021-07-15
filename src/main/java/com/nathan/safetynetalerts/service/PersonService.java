package com.nathan.safetynetalerts.service;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

	public String getPerson() {
		return "Get working";
	}

	public String postPerson() {
		return "Post working";
	}

	public String putPerson() {
		return "Put Working";
	}

	public String deletePerson() {
		return "Delete working";
	}
}
