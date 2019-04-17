package com.itv.checkout.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.itv.checkout.model.Product;

@RunWith(MockitoJUnitRunner.class)
public class TestProductRepository {
		
	
	@Mock
	DateUtil mockDateUtil;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@InjectMocks
	ProductRepository productRepository;
	
	@Test
	public void Should_Return_ActiveProductSet() {
		
		when(mockDateUtil.getCurrentDate()).thenReturn(Calendar.getInstance().getTime());
		Map<String, Product> products = productRepository.getProducts();
		assertFalse(products.isEmpty());
		assertEquals(4, products.size());
		assertTrue(products.containsKey("A"));
		assertTrue(products.containsKey("B"));
		assertTrue(products.containsKey("C"));
		assertTrue(products.containsKey("D"));
	}
	
	@Test
	public void Should_Return_LastWeeksProductSet() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date lastWeek = cal.getTime();
		
		when(mockDateUtil.getCurrentDate()).thenReturn(lastWeek);
		Map<String, Product> products = productRepository.getProducts();
		assertFalse(products.isEmpty());
		assertEquals(3, products.size());
		assertTrue(products.containsKey("A"));
		assertTrue(products.containsKey("B"));
		assertTrue(products.containsKey("D"));
	}
	
}
