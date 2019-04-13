package com.itv.checkout.repository;

import java.math.BigDecimal;
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
		products.put("A", this.buildProduct("A", new BigDecimal(50), 3, new BigDecimal(130)));
		products.put("B", this.buildProduct("B", new BigDecimal(30), 2, new BigDecimal(45)));
		products.put("C", this.buildProduct("C", new BigDecimal(20)));
		products.put("D", this.buildProduct("D", new BigDecimal(15)));
		return products;
	}
	
	private Product buildProduct(String sku, BigDecimal unitPrice) {
		return this.buildProduct(sku, unitPrice, 0, new BigDecimal(0));
	}
	
	private Product buildProduct(String sku, BigDecimal unitPrice, Integer qualifier, BigDecimal specialPrice) {
		Product product = new Product();
		product.setSku(sku);
		product.setUnitPrice(unitPrice);
		product.setQualifier(qualifier);
		product.setSpecialPrice(specialPrice);
		return product;
	}
	

}
