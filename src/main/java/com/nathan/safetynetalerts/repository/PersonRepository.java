package com.nathan.safetynetalerts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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
		List<Person> existingPersons = getExistingPersons(personToPut);
		if (existingPersons.isEmpty() || existingPersons.size() >= 2) {
			return null;
		} else {
			getPersons().remove(existingPersons.get(0));
			getPersons().add(personToPut);
			return personToPut;
		}
	}

	public Person deletePerson(Person personToDelete) {
		List<Person> existingPersons = getExistingPersons(personToDelete);
		if (existingPersons.isEmpty() || existingPersons.size() >= 2) {
			return null;
		} else {
			getPersons().remove(existingPersons.get(0));
			return personToDelete;
		}
	}

	public List<Person> getPersonsFromAddresses(List<String> addresses) {
		List<Person> persons = new ArrayList<>();
		for (String address : addresses) {
			persons.addAll(getPersons().stream().filter(hasAddress(address)).collect(Collectors.toList()));
		}
		return persons;
	}

	public List<Person> getPersonsFromAddress(String address) {
		return getPersons().stream().filter(hasAddress(address)).collect(Collectors.toList());
	}

	public List<Person> getPersonsFromCity(String city) {
		return getPersons().stream().filter(p -> p.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
	}

	private List<Person> getExistingPersons(Person personToSearch) {
		List<Person> existingPerson = getPersons().stream()
				.filter((person) -> person.getFirstName().equalsIgnoreCase(personToSearch.getFirstName())
						&& person.getLastName().equalsIgnoreCase(personToSearch.getLastName()))
				.collect(Collectors.toList());
		return existingPerson;
	}

	private Predicate<? super Person> hasAddress(String address) {
		return person -> person.getAddress().equalsIgnoreCase(address);
	}

}
