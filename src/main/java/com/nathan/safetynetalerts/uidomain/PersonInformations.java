package com.nathan.safetynetalerts.uidomain;

import java.util.ArrayList;
import java.util.List;

public class PersonInformations {
	
	private PersonInfoPerson person;
	private List<PersonInfoPerson> sameLastName = new ArrayList<>();
	
	public PersonInfoPerson getPerson() {
		return person;
	}
	public void setPerson(PersonInfoPerson person) {
		this.person = person;
	}
	public List<PersonInfoPerson> getSameLastName() {
		return sameLastName;
	}
	public void setSameLastName(List<PersonInfoPerson> sameLastName) {
		this.sameLastName = sameLastName;
	}

}
