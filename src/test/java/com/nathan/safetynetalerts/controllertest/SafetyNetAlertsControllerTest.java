package com.nathan.safetynetalerts.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nathan.safetynetalerts.controller.SafetyNetAlertsController;
import com.nathan.safetynetalerts.model.Firestation;
import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.model.Person;
import com.nathan.safetynetalerts.service.FirestationService;
import com.nathan.safetynetalerts.service.MedicalRecordService;
import com.nathan.safetynetalerts.service.PersonService;
import com.nathan.safetynetalerts.uidomain.ChildAlertPerson;
import com.nathan.safetynetalerts.uidomain.ChildAlertPersonFromAddress;
import com.nathan.safetynetalerts.uidomain.FireAndFloodPerson;
import com.nathan.safetynetalerts.uidomain.FireInformations;
import com.nathan.safetynetalerts.uidomain.FloodStationsInformations;
import com.nathan.safetynetalerts.uidomain.PersonInfoPerson;
import com.nathan.safetynetalerts.uidomain.PersonInformations;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SafetyNetAlertsController.class)
public class SafetyNetAlertsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	FirestationService firestationService;

	@MockBean
	PersonService personService;

	@MockBean
	MedicalRecordService medicalRecordService;

	private static Person person = new Person();
	private static String pFirstName;
	private static String pLastName;
	private static String pAddress;
	private static String city;
	private static String zip;
	private static String phone;
	private static String email;

	private static Firestation firestation = new Firestation();
	private static String fAddress;
	private static int station;

	private static MedicalRecord medicalRecord = new MedicalRecord();
	private static String mFirstName;
	private static String mLastName;
	private static String birthdate;
	private static ArrayList<String> medications = new ArrayList<>();
	private static ArrayList<String> allergies = new ArrayList<>();

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	private static void setUp() {
		pFirstName = "First";
		pLastName = "Last";
		pAddress = "01 Test Address";
		city = "City";
		zip = "12345";
		phone = "000-111-2222";
		email = "test@email.com";
		fAddress = "01 Test Address";
		station = 8;
		mFirstName = "First";
		mLastName = "Last";
		birthdate = "12/31/1990";
		medications.add("Medication 01");
		medications.add("Medication 02");
		allergies.add("Allergy 01");
		allergies.add("Allergy 02");

		person.setFirstName(pFirstName);
		person.setLastName(pLastName);
		person.setAddress(pAddress);
		person.setCity(city);
		person.setZip(zip);
		person.setPhone(phone);
		person.setEmail(email);
		firestation.setAddress(fAddress);
		firestation.setStation(station);
		medicalRecord.setFirstName(mFirstName);
		medicalRecord.setLastName(mLastName);
		medicalRecord.setBirthdate(birthdate);
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
	}

	@Test
	public void getChildAlert_withOneChild() throws Exception {
		ChildAlertPersonFromAddress expected = new ChildAlertPersonFromAddress();
		ChildAlertPerson expectedPerson = new ChildAlertPerson();
		List<ChildAlertPerson> expectedList = new ArrayList<>();
		String requestParam = person.getAddress();
		int age = 5;
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		expectedPerson.setFirstName(person.getFirstName());
		expectedPerson.setLastName(person.getLastName());
		expectedPerson.setAge(age);
		expectedList.add(expectedPerson);
		expected.setChildren(expectedList);
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/childAlert").param("address", requestParam);
		Mockito.when(personService.getPersonsFromAddress(requestParam)).thenReturn(persons);
		Mockito.when(medicalRecordService.getPersonAge(Mockito.any(Person.class))).thenReturn(age);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}

	@Test
	public void getChildAlert_withOneAdult_shouldReturnEmptyString() throws Exception {
		String requestParam = person.getAddress();
		int age = 88;
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		RequestBuilder request = MockMvcRequestBuilders.get("/childAlert").param("address", requestParam);
		Mockito.when(personService.getPersonsFromAddress(requestParam)).thenReturn(persons);
		Mockito.when(medicalRecordService.getPersonAge(Mockito.any(Person.class))).thenReturn(age);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contentEquals(""));
	}

	@Test
	public void getPhoneAlert() throws Exception {
		Integer requestParam = firestation.getStation();
		List<String> addresses = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		Set<String> expected = new TreeSet<>();
		addresses.add(person.getAddress());
		persons.add(person);
		expected.add(person.getPhone());
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/phoneAlert").param("firestation",
				requestParam.toString());
		Mockito.when(firestationService.getAddressesFromStationNumber(requestParam)).thenReturn(addresses);
		Mockito.when(personService.getPersonsFromAddresses(addresses)).thenReturn(persons);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(person.getPhone()));
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}

	@Test
	public void getPhoneAlert_withNoPersonFound_shouldReturnEmptyTreeSet() throws Exception {
		Integer requestParam = firestation.getStation();
		List<String> addresses = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		Set<String> expected = new TreeSet<>();
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/phoneAlert").param("firestation",
				requestParam.toString());
		Mockito.when(firestationService.getAddressesFromStationNumber(requestParam)).thenReturn(addresses);
		Mockito.when(personService.getPersonsFromAddresses(addresses)).thenReturn(persons);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}

	@Test
	public void getFire() throws Exception {
		FireInformations expected = new FireInformations();
		String requestParam = pAddress;
		int age = 18;
		FireAndFloodPerson fireAndFloodPerson = new FireAndFloodPerson(person.getLastName(), person.getFirstName(),
				person.getPhone(), age, medicalRecord.getMedications(), medicalRecord.getAllergies());
		List<FireAndFloodPerson> fireAndFloodPersons = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		fireAndFloodPersons.add(fireAndFloodPerson);
		expected.setStationNumber(firestation.getStation());
		expected.setPersons(fireAndFloodPersons);
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/fire").param("address", requestParam);
		Mockito.when(personService.getPersonsFromAddress(requestParam)).thenReturn(persons);
		Mockito.when(medicalRecordService.getPersonAge(Mockito.any(Person.class))).thenReturn(age);
		Mockito.when(medicalRecordService.getMedications(Mockito.any(Person.class))).thenReturn(medications);
		Mockito.when(medicalRecordService.getAllergies(Mockito.any(Person.class))).thenReturn(allergies);
		Mockito.when(firestationService.getStationNumberFromAddress(requestParam)).thenReturn(station);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}

	@Test
	public void getFloodStations() throws Exception {
		FloodStationsInformations expected = new FloodStationsInformations();
		String requestParam = "8";
		int age = 18;
		FireAndFloodPerson fireAndFloodPerson = new FireAndFloodPerson(person.getLastName(), person.getFirstName(),
				person.getPhone(), age, medicalRecord.getMedications(), medicalRecord.getAllergies());
		List<Integer> stations = Arrays.asList(8);
		List<String> addresses = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		List<FireAndFloodPerson> fireAndFloodPersons = new ArrayList<>();
		Map<String, List<FireAndFloodPerson>> personsFromAddress = new TreeMap<>();
		addresses.add(person.getAddress());
		persons.add(person);
		fireAndFloodPersons.add(fireAndFloodPerson);
		personsFromAddress.put(addresses.get(0), fireAndFloodPersons);
		expected.setPersonsFromAddress(personsFromAddress);
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/flood/stations").param("stations",
				requestParam.toString());
		Mockito.when(firestationService.getAddressesFromStationNumbers(stations)).thenReturn(addresses);
		Mockito.when(personService.getPersonsFromAddress(person.getAddress())).thenReturn(persons);
		Mockito.when(medicalRecordService.getPersonAge(Mockito.any(Person.class))).thenReturn(age);
		Mockito.when(medicalRecordService.getMedications(Mockito.any(Person.class))).thenReturn(medications);
		Mockito.when(medicalRecordService.getAllergies(Mockito.any(Person.class))).thenReturn(allergies);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}
	
	@Test
	public void getPersonInfo_withOneSameLastName() throws Exception {
		PersonInformations expected = new PersonInformations();
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		int age = 18;
		PersonInfoPerson expectedInfoPerson = new PersonInfoPerson(person.getFirstName(), person.getLastName(),
				person.getAddress(), age, person.getEmail(), medicalRecord.getMedications(),
				medicalRecord.getAllergies());
		PersonInfoPerson expectedSameLastNameInfoPerson = new PersonInfoPerson(person.getFirstName(), person.getLastName(),
				person.getAddress(), age, person.getEmail(), medicalRecord.getMedications(),
				medicalRecord.getAllergies());
		Person expectedSameLastNamePerson = new Person();
		List<Person> persons = new ArrayList<>();
		List<PersonInfoPerson> expectedInfoPersons = new ArrayList<>();
		List<PersonInfoPerson> expectedSameLastName = new ArrayList<>();
		persons.add(person);
		expectedSameLastNamePerson.setFirstName(pFirstName);
		expectedSameLastNamePerson.setLastName(pLastName);
		expectedSameLastNamePerson.setAddress(pAddress);
		expectedSameLastNamePerson.setCity(city);
		expectedSameLastNamePerson.setZip(zip);
		expectedSameLastNamePerson.setPhone(phone);
		expectedSameLastNamePerson.setEmail(email);
		persons.add(expectedSameLastNamePerson);
		expectedInfoPersons.add(expectedInfoPerson);
		expectedSameLastName.add(expectedSameLastNameInfoPerson);
		requestParams.add("firstName", person.getFirstName());
		requestParams.add("lastName", person.getLastName());
		expected.setPerson(expectedInfoPerson);
		expected.setSameLastName(expectedSameLastName);
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/personInfo").params(requestParams);
		Mockito.when(personService.getPersons()).thenReturn(persons);
		Mockito.when(medicalRecordService.getPersonAge(Mockito.any(Person.class))).thenReturn(age);
		Mockito.when(medicalRecordService.getMedications(Mockito.any(Person.class))).thenReturn(medications);
		Mockito.when(medicalRecordService.getAllergies(Mockito.any(Person.class))).thenReturn(allergies);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}
	
	@Test
	public void getPersonInfo_withNoSameLastName() throws Exception {
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		int age = 18;
		PersonInfoPerson expected = new PersonInfoPerson(person.getFirstName(), person.getLastName(),
				person.getAddress(), age, person.getEmail(), medicalRecord.getMedications(),
				medicalRecord.getAllergies());
		List<Person> persons = new ArrayList<>();
		List<PersonInfoPerson> expectedInfoPersons = new ArrayList<>();
		persons.add(person);
		expectedInfoPersons.add(expected);
		requestParams.add("firstName", person.getFirstName());
		requestParams.add("lastName", person.getLastName());
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/personInfo").params(requestParams);
		Mockito.when(personService.getPersons()).thenReturn(persons);
		Mockito.when(medicalRecordService.getPersonAge(Mockito.any(Person.class))).thenReturn(age);
		Mockito.when(medicalRecordService.getMedications(Mockito.any(Person.class))).thenReturn(medications);
		Mockito.when(medicalRecordService.getAllergies(Mockito.any(Person.class))).thenReturn(allergies);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}

	@Test
	public void getPersonInfo_withNoPersonFound() throws Exception {
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("firstName", "Error");
		requestParams.add("lastName", "Error");
		RequestBuilder request = MockMvcRequestBuilders.get("/personInfo").params(requestParams);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains("Cette personne n'existe pas"));
	}

	@Test
	public void getCommunityEmail() throws Exception {
		String requestParam = person.getCity();
		List<Person> persons = new ArrayList<>();
		Set<String> expected = new TreeSet<>();
		persons.add(person);
		expected.add(person.getEmail());
		String expectedJson = objectMapper.writeValueAsString(expected);
		RequestBuilder request = MockMvcRequestBuilders.get("/communityEmail").param("city", requestParam);
		Mockito.when(personService.getPersonsFromCity(requestParam)).thenReturn(persons);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertEquals(result.getResponse().getContentAsString(), expectedJson);
	}
}
