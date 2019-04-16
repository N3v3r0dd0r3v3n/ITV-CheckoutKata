package com.itv.checkout.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.itv.checkout.model.Product;

@Repository
public class ProductRepository {
	
	Logger logger = LogManager.getLogger(ProductRepository.class);
	
	private Set<ProductSet> productSets = new HashSet<ProductSet>();
	
	public ProductRepository() {
		super();
		productSets.add(buildActiveSet());
		productSets.add(buildArchiveSet());
	}
	
	protected ProductRepository(Map<String, Product> nonActiveProducts) {
		super();
		ProductSet nonActiveSet = new ProductSet(false, nonActiveProducts);
		productSets.add(nonActiveSet);
	}
	
	private ProductSet buildActiveSet() {
		Map<String, Product> products = new HashMap<String, Product>();
		products.put("A", this.buildProduct("A", 50, 3, 130));
		products.put("B", this.buildProduct("B", 30, 2, 45));
		products.put("C", this.buildProduct("C", 20));
		products.put("D", this.buildProduct("D", 15));
		
		ProductSet productSet = new ProductSet(true, products);
		return productSet;
	}
	
	private ProductSet buildArchiveSet() {
		Map<String, Product> products = new HashMap<String, Product>();
		products.put("A", this.buildProduct("A", 45, 3, 110));
		products.put("B", this.buildProduct("B", 25, 2, 40));
		products.put("D", this.buildProduct("D", 15));
		ProductSet productSet = new ProductSet(false, products);
		return productSet;
	}
	
	public Map<String, Product> getCurrentProducts() {
		Map<String, Product> products  = new HashMap<String, Product>();
		for (ProductSet productSet: productSets) {
			if (productSet.isActive()) {
				products = productSet.getProducts();
				break;
			}
		}
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
	
	private class ProductSet {
		private boolean active;
		private Map<String, Product> products;
		
		public ProductSet(boolean isActive, Map<String, Product> products) {
			super();
			this.active = isActive;
			this.products = products;
		}
		
		public boolean isActive() {
			return active;
		}
		public Map<String, Product> getProducts() {
			return products;
		}
	}
}
