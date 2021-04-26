package com.chat.aibot.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.chat.aibot.constants.Constants;
import com.chat.aibot.models.Intents;
import com.chat.aibot.models.Response;
import com.chat.aibot.repositories.DataObjectRepository;
import com.chat.aibot.services.BotService;

@Service
public class BotServiceImpl implements BotService{
	
	@Autowired
	private DataObjectRepository repository;
	
	RestTemplate restTemplate = null;

	/**
	 * This method actually makes the post call to the API and finds the intent of the user and sends response.
	 * @param botId
	 * @param message
	 * @return Bot's actual response to the user
	 */
	@Override
	public String getResponse(String botId, String message) {
		
		ResponseEntity<Response> response = makePostCall(botId, message);
		
		List<Intents> intents = response.getBody().getIntents();
		
		Intents intentWithMaxConfidence = Collections.max(intents);
		
		if(intentWithMaxConfidence.getConfidence()>Constants.CONFIDENCE_THRESHOLD) {
			//Queries the DB for the reply corresponding to the user's intent
			return repository.findByName(intentWithMaxConfidence.getName()).getReply().getText();
		}		
		return Constants.BOT_DOES_NOT_GET_IT_RESPONSE;
	}

	/**
	 * This method actually makes the post call to the API.
	 * @param botId
	 * @param message
	 * @return response
	 */
	private ResponseEntity<Response> makePostCall(String botId, String message) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", Constants.API_KEY);
		headers.add("Content-Type", "application/json");

		HttpEntity<?> httpEntity = new HttpEntity<Object>(framePayLoad(botId, message), headers);
		ResponseEntity<Response> response = getRestTemplate().postForEntity(Constants.API_URL, httpEntity, Response.class);
		return response;
	}

	/**
	 * This method is used to construct the input payload to the API.
	 * @param botId
	 * @param message
	 * @return Payload
	 */
	private String framePayLoad(String botId, String message) {
		return "{\"botId\":\""+botId+"\",\"message\":\""+message+"\"}";
	}
	
	
	/**
	 * This method is used to maintain singleton design pattern.
	 * @return RestTemplate object
	 */
	private RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			return new RestTemplate();
		}
		return restTemplate;
	}
}
