package com.itv.checkout.integration;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.itv.checkout.Application;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCheckoutController {
		
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
		
	@Test
	public void Should_Return_BadRequest_WhenBodyEmpty() throws Exception {
		headers.add("Content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/checkout"),
				HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());	
	}
	
	@Test
	public void Should_Return_BadRequest_WhenInvalidBody() throws Exception {
		headers.add("Content-type", "application/json");
		Integer invalidBody = 0;
		HttpEntity<Integer> entity = new HttpEntity<Integer>(invalidBody, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/checkout"),
				HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void Should_Return_OK_WhenValidBody() throws Exception {
		headers.add("Content-type", "application/json");
		List<String> skus = new ArrayList<String>();
		skus.add("B");
		skus.add("A");
		skus.add("B");
		String expected = "0.95";
		HttpEntity<List<String>> entity = new HttpEntity<List<String>>(skus, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/checkout"),
				HttpMethod.POST, entity, String.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		//JSONAssert.assertEquals(0.95, response.getBody(), false);	
		assertEquals(expected, response.getBody());
	}
	
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
}
