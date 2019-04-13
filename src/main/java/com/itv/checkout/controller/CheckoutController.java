package com.itv.checkout.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itv.checkout.model.Greeting;
import com.itv.checkout.model.Product;
import com.itv.checkout.service.CheckoutService;

@RestController
public class CheckoutController {
	
	Logger logger = LogManager.getLogger(CheckoutController.class);
	
	@Autowired
	CheckoutService checkoutService;
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return checkoutService.getGreeting(name);
    }
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ResponseEntity<Object> update(@RequestBody List<Product> products) {
		//TODO Call checkout service	
		this.calculateTotal(products);
	    return new ResponseEntity<>(products.toString(), HttpStatus.OK);
	}
	
	private Map<String, Integer> calculateTotal(List<Product> products) {
		//TODO Move this into service.
		for (Product product: products) {
			logger.info(product);
		}
		return null;
	}
}
