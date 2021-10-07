package com.nathan.safetynetalerts.controllertest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nathan.safetynetalerts.controller.PersonController;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.PersonService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	PersonService personService;

	private static Person person = new Person();
	private static String firstName;
	private static String lastName;
	private static String address;
	private static String city;
	private static String zip;
	private static String phone;
	private static String email;

	ObjectMapper objectMapper = new ObjectMapper();

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
	public void getPersons() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		RequestBuilder request = MockMvcRequestBuilders.get("/person");
		Mockito.when(personService.getPersons()).thenReturn(persons);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(personJson));
	}

	@Test
	public void postPerson() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		RequestBuilder request = MockMvcRequestBuilders.post("/person").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(personJson);
		Mockito.when(personService.postPerson(Mockito.any(Person.class))).thenReturn(person);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 201);
		assertTrue(result.getResponse().getContentAsString().contains(personJson));
	}
	
	@Test
	public void postPerson_withError() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		RequestBuilder request = MockMvcRequestBuilders.post("/person").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(personJson);
		Mockito.when(personService.postPerson(null)).thenReturn(person);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 406);
		assertTrue(result.getResponse().getContentAsString().contains("La personne existe"));
	}
	
	@Test
	public void putPerson() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		RequestBuilder request = MockMvcRequestBuilders.put("/person").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(personJson);
		Mockito.when(personService.putPerson(Mockito.any(Person.class))).thenReturn(person);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(personJson));
	}
	
	@Test
	public void putPerson_withError() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		RequestBuilder request = MockMvcRequestBuilders.put("/person").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(personJson);
		Mockito.when(personService.putPerson(null)).thenReturn(person);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 404);
		assertTrue(result.getResponse().getContentAsString().contains("La personne n'existe pas"));
	}
	
	@Test
	public void deletePerson() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		RequestBuilder request = MockMvcRequestBuilders.delete("/person").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(personJson);
		Mockito.when(personService.deletePerson(Mockito.any(Person.class))).thenReturn(person);
		
		MvcResult result = mvc.perform(request).andReturn();
		
		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(personJson));
	}
	
	@Test
	public void deletePerson_withError() throws Exception {
		String personJson = objectMapper.writeValueAsString(person);
		RequestBuilder request = MockMvcRequestBuilders.delete("/person").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(personJson);
		Mockito.when(personService.deletePerson(null)).thenReturn(person);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 404);
		assertTrue(result.getResponse().getContentAsString().contains("La personne n'existe pas"));
	}
}
