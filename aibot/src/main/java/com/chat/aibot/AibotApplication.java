package com.chat.aibot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chat.aibot.constants.Constants;
import com.chat.aibot.models.DataObject;
import com.chat.aibot.repositories.DataObjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class AibotApplication  implements CommandLineRunner{
	
	@Autowired
	private DataObjectRepository repository;

	/**
	 * This method is used to load default data from intents.json file (in project's root folder) into the database.
	 */
	@Override
	public void run(String... args) throws Exception {
		Path fileName = Path.of(Constants.INITIAL_DATA_FILE);
        String fileData = null;
		try {
			fileData = Files.readString(fileName);
		} catch (NoSuchFileException fnf) {
			System.out.println(Constants.FILE_NOT_AVAILABLE);
			fnf.printStackTrace();
		} catch (IOException io) {
			System.out.println(io.getMessage());
			io.printStackTrace();
		}
	    
		//Clearing any previous documents. This step is optional.
        repository.deleteAll();
        
        //Parsing the file contents and converting them into a JsonArray.
		JSONArray jsonArray = new JSONArray(fileData);
		
		for (Object currentObject : jsonArray) {	
			//Using ObjectMapper to map the object to POJO of type DataObject.
	        ObjectMapper mapper = new ObjectMapper();
	        DataObject document = mapper.readValue(currentObject.toString(), DataObject.class);
	        
	        //Persist in DB.
			repository.save(document);			
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AibotApplication.class, args);
	}
}
