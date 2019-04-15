package com.itv.checkout.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.itv.checkout.model.Product;

@Repository
public class ProductRepository {
	
	Logger logger = LogManager.getLogger(ProductRepository.class);
	
	public Map<String, Product> getProducts() {
		//TODO Override with date at some point for different data-sets
		Map<String, Product> products = new HashMap<String, Product>();
		products.put("A", this.buildProduct("A",50, 3, 130));
		products.put("B", this.buildProduct("B", 30, 2, 45));
		products.put("C", this.buildProduct("C", 20));
		products.put("D", this.buildProduct("D", 15));
		return products;
	}
	
	private Product buildProduct(String sku, Integer unitPrice) {
		return this.buildProduct(sku, unitPrice, 0, 0);
	}
	
	private Product buildProduct(String sku, Integer unitPrice, Integer qualifier, Integer specialPrice) {
		Product product = new Product();
		product.setSku(sku);
		product.setUnitPrice(unitPrice);
		product.setQualifier(qualifier);
		product.setSpecialPrice(specialPrice);
		return product;
	}
	

}
