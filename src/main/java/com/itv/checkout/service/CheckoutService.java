package com.itv.checkout.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itv.checkout.model.Greeting;
import com.itv.checkout.model.Product;

@Service
public class CheckoutService {
	
	Logger logger = LogManager.getLogger(CheckoutService.class);
	
	private final AtomicLong counter = new AtomicLong();
	
	public Greeting getGreeting(String name) {
		final String template = "Hello, %s";
		return new Greeting(counter.incrementAndGet(),
	            String.format(template, name));	
	}
	
	public Object checkout(List<Product> products) {
		//TODO Call to rules engine based on date (perhaps)
		logger.info("log message");
		return null;
	}
	
}