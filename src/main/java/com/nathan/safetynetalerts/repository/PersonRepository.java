package com.nathan.safetynetalerts.repository;

import java.util.ArrayList;
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

		boolean alreadyExists = getPersons().stream()
				.anyMatch((person) -> person.getFirstName().equalsIgnoreCase(personToPost.getFirstName())
						&& person.getLastName().equalsIgnoreCase(personToPost.getLastName()));

		if (alreadyExists == false) {
			getPersons().add(personToPost);
			return personToPost;
		}
		return null;
	}

	public Person putPerson(Person personToPut) {

		List<Person> existingPerson = getPersons().stream()
				.filter((person) -> person.getFirstName().equalsIgnoreCase(personToPut.getFirstName())
						&& person.getLastName().equalsIgnoreCase(personToPut.getLastName()))
				.collect(Collectors.toList());

		if (existingPerson.isEmpty() || existingPerson.size() >= 2) {
			return null;
		} else {
			getPersons().remove(existingPerson.get(0));
			getPersons().add(personToPut);
			return personToPut;
		}
	}

	public Person deletePerson(Person personToDelete) {

		List<Person> existingPerson = getPersons().stream()
				.filter((person) -> person.getFirstName().equalsIgnoreCase(personToDelete.getFirstName())
						&& person.getLastName().equalsIgnoreCase(personToDelete.getLastName()))
				.collect(Collectors.toList());

		if (existingPerson.isEmpty() || existingPerson.size() >= 2) {
			return null;
		} else {
			getPersons().remove(existingPerson.get(0));
			return personToDelete;
		}
	}

	public List<Person> getPersonsFromAddresses(List<String> addresses) {
		List<Person> persons = new ArrayList<>();
		for (String address : addresses) {
			persons.addAll(getPersons().stream().filter(person -> person.getAddress().equalsIgnoreCase(address))
					.collect(Collectors.toList()));
		}
		return persons;
	}

	public List<Person> getPersonsFromAddress(String address) {
		List<Person> persons = new ArrayList<>();
		persons.addAll(getPersons().stream().filter(person -> person.getAddress().equalsIgnoreCase(address))
				.collect(Collectors.toList()));
		return persons;
	}

	public List<Person> getPersonsFromCity(String city) {
		List<Person> persons = new ArrayList<>();
		persons.addAll(getPersons().stream()
				.filter(p -> p.getCity().equalsIgnoreCase(city))
				.collect(Collectors.toList()));
		return persons;
	}
}
