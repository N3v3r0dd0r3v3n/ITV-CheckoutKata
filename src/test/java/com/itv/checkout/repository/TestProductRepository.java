package com.itv.checkout.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.itv.checkout.model.Product;


@RunWith(MockitoJUnitRunner.class)
public class TestProductRepository {
		
	ProductRepository productRepository;
	
	@Test
	public void Should_Return_ActiveProductSet() {
		productRepository = new ProductRepository();
		Map<String, Product> products = productRepository.getCurrentProducts();
		assertFalse(products.isEmpty());
		assertEquals(4, products.size());
		assertTrue(products.containsKey("A"));
		assertTrue(products.containsKey("B"));
		assertTrue(products.containsKey("C"));
		assertTrue(products.containsKey("D"));
	}
	
	@Test
	public void Should_Return_Empty_WhenNoActiveProductSet() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(10);
		product.setQualifier(2);
		product.setSpecialPrice(15);
		
		Map<String, Product> productForNonActiveSet = new HashMap<String, Product>();
		
		productRepository = new ProductRepository(productForNonActiveSet);
		Map<String, Product> products = productRepository.getCurrentProducts();
		assertTrue(products.isEmpty());
	}
	
}
