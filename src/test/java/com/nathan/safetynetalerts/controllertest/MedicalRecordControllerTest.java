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
import com.nathan.safetynetalerts.controller.MedicalRecordController;
import com.nathan.safetynetalerts.model.MedicalRecord;
import com.nathan.safetynetalerts.service.MedicalRecordService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MedicalRecordController.class)
public class MedicalRecordControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	MedicalRecordService medicalRecordService;

	private static MedicalRecord medicalRecord = new MedicalRecord();
	private static String firstName;
	private static String lastName;
	private static String birthdate;
	private static ArrayList<String> medications = new ArrayList<>();
	private static ArrayList<String> allergies = new ArrayList<>();

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeAll
	private static void setUpMedicalRecord() {
		firstName = "First";
		lastName = "Last";
		birthdate = "12/31/1990";
		medications.add("Medication 01");
		medications.add("Medication 02");
		allergies.add("Allergy 01");
		allergies.add("Allergy 02");

		medicalRecord.setFirstName(firstName);
		medicalRecord.setLastName(lastName);
		medicalRecord.setBirthdate(birthdate);
		medicalRecord.setMedications(medications);
		medicalRecord.setAllergies(allergies);
	}

	@Test
	public void getMedicalRecords() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		medicalRecords.add(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.get("/medicalRecord");
		Mockito.when(medicalRecordService.getMedicalRecords()).thenReturn(medicalRecords);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains(medicalRecordJson));
	}

	@Test
	public void postMedicalRecords() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.post("/medicalRecord").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson);
		Mockito.when(medicalRecordService.postMedicalRecord(Mockito.any(MedicalRecord.class)))
				.thenReturn(medicalRecord);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 201);
		assertTrue(result.getResponse().getContentAsString().contains(medicalRecordJson));
	}

	@Test
	public void postMedicalRecords_withError() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.post("/medicalRecord").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson);
		Mockito.when(medicalRecordService.postMedicalRecord(null)).thenReturn(medicalRecord);

		MvcResult result = mvc.perform(request).andReturn();
		
		
		assertTrue(result.getResponse().getStatus() == 406);
		assertTrue(result.getResponse().getContentAsString().contains("Le dossier mÃ©dical existe dÃ©jÃ"));
	}
	
	@Test
	public void putMedicalRecords() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.put("/medicalRecord").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson);
		Mockito.when(medicalRecordService.putMedicalRecord(Mockito.any(MedicalRecord.class)))
				.thenReturn(medicalRecord);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(medicalRecordJson));
	}

	@Test
	public void putMedicalRecords_withError() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.put("/medicalRecord").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson);
		Mockito.when(medicalRecordService.putMedicalRecord(null)).thenReturn(medicalRecord);

		MvcResult result = mvc.perform(request).andReturn();
		
		assertTrue(result.getResponse().getStatus() == 404);
		assertTrue(result.getResponse().getContentAsString().contains("Le dossier mÃ©dical n'existe pas"));
	}
	
	@Test
	public void deleteMedicalRecords() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.delete("/medicalRecord").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson);
		Mockito.when(medicalRecordService.deleteMedicalRecord(Mockito.any(MedicalRecord.class)))
				.thenReturn(medicalRecord);

		MvcResult result = mvc.perform(request).andReturn();

		assertTrue(result.getResponse().getStatus() == 200);
		assertTrue(result.getResponse().getContentAsString().contains(medicalRecordJson));
	}

	@Test
	public void deleteMedicalRecords_withError() throws Exception {
		String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);
		RequestBuilder request = MockMvcRequestBuilders.delete("/medicalRecord").characterEncoding("utf-8")
				.contentType(MediaType.APPLICATION_JSON).content(medicalRecordJson);
		Mockito.when(medicalRecordService.deleteMedicalRecord(null)).thenReturn(medicalRecord);

		MvcResult result = mvc.perform(request).andReturn();
		
		assertTrue(result.getResponse().getStatus() == 404);
		assertTrue(result.getResponse().getContentAsString().contains("Le dossier mÃ©dical n'existe pas"));
	}

}
