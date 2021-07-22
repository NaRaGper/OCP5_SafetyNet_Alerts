package com.nathan.safetynetalerts.init;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nathan.safetynetalerts.database.Database;

@Component
public class StartUpImplement implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		System.out.println("démarrage de l'application");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Datas datas = objectMapper.readValue(new File("src/main/resources/data.json"), Datas.class);
		
		Database.getInstance().setData(datas);
	}
	
	
	
}
