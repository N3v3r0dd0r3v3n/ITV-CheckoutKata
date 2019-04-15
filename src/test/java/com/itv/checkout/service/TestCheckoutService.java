package com.itv.checkout.service;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestCheckoutService {
		
	private CheckoutService checkoutService;
	
	@Before
	public void setup() {
		checkoutService = new CheckoutService();
	}
	
	@Test
	public void Should_Return_ZeroTotal_WhenSkuListIsEmpty() {
		//BigDecimal expected = new BigDecimal(0);
		//BigDecimal total = checkoutService.checkout(new ArrayList<String>());
		//assertEquals(expected, total);
	}

}
