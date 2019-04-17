package com.itv.checkout.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itv.checkout.service.CheckoutService;

@RestController
public class CheckoutController {
	
	Logger logger = LogManager.getLogger(CheckoutController.class);
	
	@Autowired
	CheckoutService checkoutService;
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ResponseEntity<Float> checkout(@RequestBody List<String> items) {
				
		logger.info(String.format("CheckoutController recieved request %s", items));
		
		
		List<String> skus = new ArrayList<String>();
		for (String item: items) {
			skus.add(item);
		}
		
		Float total = checkoutService.checkout(skus);
		logger.info(String.format("Checkout total: %s", total));
		
		return new ResponseEntity<>(total, HttpStatus.OK);
	}
}
