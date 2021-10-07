package com.nathan.safetynetalerts.repositorytest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nathan.safetynetalerts.database.Database;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.repository.PersonRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonRepositoryTest {

	@Autowired
	PersonRepository personRepository;

	private static Database database = Database.getInstance();

	private Person person = new Person();
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	private void setUp() {
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

	@AfterEach
	public void cleanUp() {
		if (personRepository.getPersons().contains(person)) {
			personRepository.deletePerson(person);
		}
	}

	@Test
	public void getPersons() {
		try (MockedStatic<Database> mock = Mockito.mockStatic(Database.class)) {
			mock.when(Database::getInstance).thenReturn(database);

			List<Person> persons = personRepository.getPersons();

			assertTrue(!persons.isEmpty());
		}
	}

	@Test
	public void postPerson_whenPersonDoesntExist_shouldReturnPerson() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			Person result = personRepository.postPerson(person);
			List<Person> persons = personRepository.getPersons();

			assertNotNull(result);
			assertTrue(persons.contains(result));
		}
	}

	@Test
	public void postPerson_whenPersonExists_shouldReturnNull() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			
			personRepository.postPerson(person);
			Person result = personRepository.postPerson(person);
			List<Person> persons = personRepository.getPersons();

			assertNull(result);
			assertTrue(!persons.contains(result));
		}
	}
	
	@Test
	public void putPerson_whenPersonDoesntExist_shouldReturnNull() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			Person result = personRepository.putPerson(person);
			List<Person> persons = personRepository.getPersons();
			
			assertNull(result);
			assertTrue(!persons.contains(result));
		}
	}

	@Test
	public void putPerson_whenPersonExists_shouldReturnModifiedPerson() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			person = personRepository.postPerson(person);
			person.setCity("New city");
			
			Person result = personRepository.putPerson(person);

			assertNotNull(result);
			assertTrue(result.getCity().equalsIgnoreCase("New city"));
		}
	}
	
	@Test
	public void deletePerson_whenPersonDoesntExist_shouldReturnNull() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);

			Person result = personRepository.deletePerson(person);
			List<Person> persons = personRepository.getPersons();
			
			assertNull(result);
			assertTrue(!persons.contains(result));
		}
	}

	@Test
	public void deletePerson_whenPersonExists_shouldReturnDeletedPerson() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			
			personRepository.postPerson(person);
			Person result = personRepository.deletePerson(person);
			List<Person> persons = personRepository.getPersons();
			
			assertNotNull(result);
			assertTrue(!persons.contains(result));
		}
	}
	
	@Test
	public void getPersonsFromAddresses() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			List<String> addresses = new ArrayList<>();
			addresses.add(address);
			
			personRepository.postPerson(person);
			List<Person> result = personRepository.getPersonsFromAddresses(addresses);
			
			assertTrue(result.size() == 1);
			assertTrue(result.toString().contains(address));
		}
	}
	
	@Test
	public void getPersonsFromAddress() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			
			personRepository.postPerson(person);
			List<Person> result = personRepository.getPersonsFromAddress(address);
			
			assertTrue(result.size() == 1);
			assertTrue(result.toString().contains(address));
		}
	}
	
	@Test
	public void getPersonsFromCity() {
		try (MockedStatic<Database> utilities = Mockito.mockStatic(Database.class)) {
			utilities.when(Database::getInstance).thenReturn(database);
			
			personRepository.postPerson(person);
			List<Person> result = personRepository.getPersonsFromCity(city);
			
			assertTrue(result.size() == 1);
			assertTrue(result.toString().contains(city));
		}
	}
}
