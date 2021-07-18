package com.nathan.safetynetalerts.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.Person;

@Repository
public class PersonRepository {
	
	public List<Person> getAllPersons(){
		return Database.getInstance().getData().getPersons();
		
	}

	public void deletePerson(Person personToDelete) {
		List<Person> persons = Database.getInstance().getData().getPersons();
		
		for (Person person : persons) {
			if (person.getFirstName().equalsIgnoreCase(personToDelete.getFirstName()) && person.getLastName().equalsIgnoreCase(personToDelete.getLastName())) {
				Database.getInstance().getData().getPersons().remove(person);
				break;
			}
				
		}
	}
}
