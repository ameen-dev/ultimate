package com.chat.aibot;



import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.chat.aibot.constants.Constants;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AibotApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String userMessage) {
		return Constants.URL_PART1 + port + Constants.URL_PART2 + userMessage;
	}

	@Test
	public void testGreetingIntent() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String userMessage = "Hi";
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(userMessage), HttpMethod.GET, entity, String.class);
		String expected = "Hello :) How can I help you?";
		assertEquals(expected, response.getBody());
	}
	
	@Test
	public void testNoIntentMatches() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String userMessage = "Hello this is a chat message";
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(userMessage), HttpMethod.GET, entity, String.class);
		String expected = "Sorry! I am not able to help you with that. Could I help you with something else?";
		assertEquals(expected, response.getBody());
	}
	
	@Test
	public void testNeedPersonIntent() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String userMessage = "Could I talk to a person";
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(userMessage), HttpMethod.GET, entity, String.class);
		String expected = "Let me transfer you to the first available agent.";
		assertEquals(expected, response.getBody());
	}
	
	@Test
	public void testInvalidInput() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String userMessage = "Peacock and pigeon are birds";
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort(userMessage), HttpMethod.GET, entity, String.class);
		String expected = "Sorry! I am not able to help you with that. Could I help you with something else?";
		assertEquals(expected, response.getBody());
	}

}