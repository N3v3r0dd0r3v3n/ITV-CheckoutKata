package com.itv.checkout.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.itv.checkout.service.CheckoutService;


@RunWith(MockitoJUnitRunner.class)
public class TestCheckoutController {
	
	@Mock
	CheckoutService mockCheckoutService;
	
	@InjectMocks
	CheckoutController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
		
	@Test
	public void Should_Return_OK_WhenSKUListIsEmpty() {
		when(mockCheckoutService.checkout(new ArrayList<String>())).thenReturn(0f);
		ResponseEntity<Float> response = controller.checkout(new ArrayList<String>());
		assertEquals(new Float(0), response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());		
	}
	
	@Test
	public void Should_Return_OK_WhenSKUListIsNotEmpty() {
		List<String> skus = new ArrayList<String>();
		skus.add("A");
		ResponseEntity<Float> response = controller.checkout(skus);
		assertEquals(new Float(0), response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());	
	}
	
}
