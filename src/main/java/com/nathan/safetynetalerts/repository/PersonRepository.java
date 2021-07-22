package com.nathan.safetynetalerts.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.Person;

@Repository
public class PersonRepository {

	public List<Person> getPersons() {
		return Database.getInstance().getData().getPersons();
	}

	public Person postPerson(Person personToPost) {

		boolean alreadyExists = getPersons().stream().anyMatch((person) -> person.getFirstName().equalsIgnoreCase(personToPost.getFirstName()) &&
				person.getLastName().equalsIgnoreCase(personToPost.getLastName()));

		if (alreadyExists == false) { 
			getPersons().add(personToPost);
			return personToPost;
		}
		return null;
	}

	public Person putPerson(Person personToPut) {

		List<Person> existingPerson = getPersons().stream().filter((person) -> person.getFirstName().equalsIgnoreCase(personToPut.getFirstName()) &&
				person.getLastName().equalsIgnoreCase(personToPut.getLastName())).collect(Collectors.toList());

		if (existingPerson.isEmpty() || existingPerson.size() >= 2) {
			return null;
		}
		else {
			getPersons().remove(existingPerson.get(0));
			getPersons().add(personToPut);
			return personToPut;
		}
	}

	public void deletePerson(Person personToDelete) {

		List<Person> persons = getPersons();

		for (Person person : persons) {
			if (person.getFirstName().equalsIgnoreCase(personToDelete.getFirstName()) &&
					person.getLastName().equalsIgnoreCase(personToDelete.getLastName())) 
			{
				persons.remove(person);
				break;
			}
		}
	}
}
