package com.nathan.safetynetalerts.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.repository.PersonRepository;
import com.nathan.safetynetalerts.service.PersonService;

@SpringBootTest
public class PersonServiceTest {

	@MockBean
	PersonRepository personRepository;

	@Autowired
	PersonService personService;

	private static Person person = new Person();
	private static String firstName;
	private static String lastName;
	private static String address;
	private static String city;
	private static String zip;
	private static String phone;
	private static String email;

	@BeforeAll
	private static void setUpPerson() {
		firstName = "First";
		lastName = "Last";
		address = "01 Test Address";
		city = "City";
		zip = "12345";
		phone = "000-111-2222";
		email = "test@email.com";

		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAddress(address);
		person.setCity(city);
		person.setZip(zip);
		person.setPhone(phone);
		person.setEmail(email);
	}

	@Test
	public void getPersons() {
		List<Person> persons = new ArrayList<>();
		Mockito.when(personRepository.getPersons()).thenReturn(persons);

		List<Person> result = personService.getPersons();

		assertTrue(result.size() == persons.size());
	}

	@Test
	public void postPerson() {
		Mockito.when(personRepository.postPerson(person)).thenReturn(person);

		Person result = personService.postPerson(person);

		assertNotNull(result);
		assertEquals(firstName, result.getFirstName());
		assertEquals(lastName, result.getLastName());
		assertEquals(address, result.getAddress());
		assertEquals(city, result.getCity());
		assertEquals(zip, result.getZip());
		assertEquals(phone, result.getPhone());
		assertEquals(email, result.getEmail());
	}

	@Test
	public void putPerson() {
		Mockito.when(personRepository.putPerson(person)).thenReturn(person);

		Person result = personService.putPerson(person);

		assertNotNull(result);
		assertEquals(firstName, result.getFirstName());
		assertEquals(lastName, result.getLastName());
		assertEquals(address, result.getAddress());
		assertEquals(city, result.getCity());
		assertEquals(zip, result.getZip());
		assertEquals(phone, result.getPhone());
		assertEquals(email, result.getEmail());
	}
	
	@Test
	public void deletePerson() {
		Mockito.when(personRepository.deletePerson(person)).thenReturn(person);

		Person result = personService.deletePerson(person);

		assertNotNull(result);
		assertEquals(firstName, result.getFirstName());
		assertEquals(lastName, result.getLastName());
		assertEquals(address, result.getAddress());
		assertEquals(city, result.getCity());
		assertEquals(zip, result.getZip());
		assertEquals(phone, result.getPhone());
		assertEquals(email, result.getEmail());
	}
	
	@Test
	public void getPersonsFromAddresses() {
		List<String> addresses = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		addresses.add("01 Test Address");
		persons.add(person);
		Mockito.when(personRepository.getPersonsFromAddresses(addresses)).thenReturn(persons);
		
		List<Person> result = personService.getPersonsFromAddresses(addresses);
		
		assertTrue(result.size() == persons.size());
	}
	
	@Test
	public void getPersonsFromAddress() {
		String address = "01 Test Address";
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		Mockito.when(personRepository.getPersonsFromAddress(address)).thenReturn(persons);
		
		List<Person> result = personService.getPersonsFromAddress(address);
		
		assertTrue(result.size() == persons.size());
	}
	
	@Test
	public void getPersonsFromCity() {
		String city = "City";
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		Mockito.when(personRepository.getPersonsFromCity(city)).thenReturn(persons);
		
		List<Person> result = personService.getPersonsFromCity(city);
		
		assertTrue(result.size() == persons.size());
	}
}
