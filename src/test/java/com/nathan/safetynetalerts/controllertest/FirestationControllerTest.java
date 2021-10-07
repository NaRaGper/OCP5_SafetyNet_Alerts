package com.nathan.safetynetalerts.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.nathan.safetynetalerts.controller.FirestationController;
import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.FirestationService;
import com.nathan.safetynetalerts.service.MedicalRecordService;
import com.nathan.safetynetalerts.service.PersonService;
import com.nathan.safetynetalerts.uidomain.UIPerson;
import com.nathan.safetynetalerts.uidomain.UIPersonFromAddress;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FirestationController.class)
public class FirestationControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	FirestationService firestationService;

	@MockBean
	PersonService personService;

	@MockBean
	MedicalRecordService medicalRecordService;

	private static Firestation firestation = new Firestation();
	private static String address;
	private static int station;
	
	private static Person person = new Person();
	private static String firstName;
	private static String lastName;
	private static String city;
	private static String zip;
	private static String phone;
	private static String email;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	private static void setUp() {
		address = "01 Test Address";
		station = 8;
		firstName = "First";
		lastName = "Last";
		city = "City";
		zip = "12345";
		phone = "000-111-2222";
		email = "test@email.com";

		firestation.setAddress(address);
		firestation.setStation(station);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAddress(address);
		person.setCity(city);
		person.setZip(zip);
		person.setPhone(phone);
		person.setEmail(email);
	}

	@Test
	public void getFirestations() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		List<Firestation> firestations = new ArrayList<>();
		firestations.add(firestation);
		RequestBuilder request = MockMvcRequestBuilders.get("/firestation/list");
		Mockito.when(firestationService.getFirestations()).thenReturn(firestations);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(firestationJson));
	}

	@Test
	public void postFirestation() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		RequestBuilder request = MockMvcRequestBuilders.post("/firestation").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(firestationJson);
		Mockito.when(firestationService.postFirestation(Mockito.any(Firestation.class))).thenReturn(firestation);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 201);
		assertTrue(result.getResponse().getContentAsString().contains(firestationJson));
	}

	@Test
	public void postFirestation_withErrors() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		RequestBuilder request = MockMvcRequestBuilders.post("/firestation").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(firestationJson);
		Mockito.when(firestationService.postFirestation(null)).thenReturn(firestation);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 406);
		assertTrue(result.getResponse().getContentAsString().contains("La caserne existe"));
	}

	@Test
	public void putFirestation() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		RequestBuilder request = MockMvcRequestBuilders.put("/firestation").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(firestationJson);
		Mockito.when(firestationService.putFirestation(Mockito.any(Firestation.class))).thenReturn(firestation);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(firestationJson));
	}

	@Test
	public void putFirestation_withErrors() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		RequestBuilder request = MockMvcRequestBuilders.put("/firestation").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(firestationJson);
		Mockito.when(firestationService.putFirestation(null)).thenReturn(firestation);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 404);
		assertTrue(result.getResponse().getContentAsString().contains("La caserne n'existe pas"));
	}

	@Test
	public void deleteFirestation() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		RequestBuilder request = MockMvcRequestBuilders.delete("/firestation").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(firestationJson);
		Mockito.when(firestationService.deleteFirestation(Mockito.any(Firestation.class))).thenReturn(firestation);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(firestationJson));
	}

	@Test
	public void deleteFirestation_withErrors() throws Exception {
		String firestationJson = objectMapper.writeValueAsString(firestation);
		RequestBuilder request = MockMvcRequestBuilders.delete("/firestation").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(firestationJson);
		Mockito.when(firestationService.deleteFirestation(null)).thenReturn(firestation);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 404);
		assertTrue(result.getResponse().getContentAsString().contains("La caserne n'existe pas"));
	}
	
	@Test
	public void getPersonsFromStation_withOneAdultAndNoChild() throws Exception {
		UIPersonFromAddress expected = new UIPersonFromAddress();
		String requestParam = "8";
		List<String> addresses = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		addresses.add(firestation.getAddress());
		persons.add(person);
		List<UIPerson> uiPersons = persons.stream().map(p -> UIPerson.toUIPerson(p)).collect(Collectors.toList());
		expected.setPersons(uiPersons);
		expected.setNombreAdultes(1);
        expected.setNombreEnfants(0);
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/firestation").param("stationNumber", requestParam);
		Mockito.when(firestationService.getAddressesFromStationNumber(Integer.valueOf(requestParam))).thenReturn(addresses);
		Mockito.when(personService.getPersonsFromAddresses(addresses)).thenReturn(persons);
		Mockito.when(medicalRecordService.getNumberOfAdults(persons)).thenReturn(1);
		
		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}

}
