package com.itv.checkout.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.itv.checkout.model.Product;

public class TestQuantifier {
	
	private final String FIRST_SKU = "A";
	private final String SECOND_SKU = "B";
	
	private Quantifier quantifier;
	
	@Before
	public void setup() {
		quantifier = new Quantifier();
	}
	
	@Test
	public void Should_Return_EmptyMap_WhenSkuListIsNull() {
		Map<String, Integer> results = quantifier.calculateQuantity(null);
		assertTrue(results.isEmpty());
	}
		
	@Test
	public void Should_Return_EmptyMap_WhenSkuListIsEmpty() {
		Map<String, Integer> results = quantifier.calculateQuantity(new ArrayList<String>());
		assertTrue(results.isEmpty());
	}

	@Test 
	public void Should_Return_TwoEntries_WhenTwoSkusSupplied() {
		List<String> skus = new ArrayList<String>();
		skus.add(FIRST_SKU);
		skus.add(SECOND_SKU);

		Map<String, Integer> results = quantifier.calculateQuantity(skus);
		assertEquals(new Integer(2), (Integer)results.size());
		assertTrue(results.containsKey(FIRST_SKU));
		assertTrue(results.containsKey(SECOND_SKU));
		assertEquals(new Integer(1), results.get(FIRST_SKU));
		assertEquals(new Integer(1), results.get(FIRST_SKU));
	}
	
	@Test
	public void Should_Return_CorrectQuantity_WhenSkuSuppliedMultipleTimes() {
		List<String> skus = new ArrayList<String>();
		skus.add(FIRST_SKU);
		skus.add(FIRST_SKU);
		
		Map<String, Integer> results = quantifier.calculateQuantity(skus);
		assertEquals(1, results.size());
		assertTrue(results.containsKey(FIRST_SKU));
		assertEquals(new Integer(2), results.get(FIRST_SKU));
	}
	
	@Test
	public void Should_Return_CorrectQuantities_WhenMultipleSkewsSuppliedMultipleTimes() {
		List<String> skus = new ArrayList<String>();
		skus.add(SECOND_SKU);
		skus.add(SECOND_SKU);
		skus.add(FIRST_SKU);
		skus.add(FIRST_SKU);
		skus.add(SECOND_SKU);
		
		Map<String, Integer> results = quantifier.calculateQuantity(skus);
		assertEquals(2, results.size());
		assertTrue(results.containsKey(FIRST_SKU));
		assertTrue(results.containsKey(SECOND_SKU));
		assertEquals(new Integer(2), results.get(FIRST_SKU));
		assertEquals(new Integer(3), results.get(SECOND_SKU));
	
	}
	
	@Test
	public void Should_Return_SubtotalOfZero_WhenNoProductSupplied() {
		Product product = null;
		Integer quantity = new Integer(1);
		Integer expected = new Integer(0);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
	
	@Test
	public void Should_Return_SubtotalOfZero_WhenNoQuantitySupplied() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(50);
		product.setQualifier(3);
		product.setSpecialPrice(130);
		
		Integer quantity = null;
		Integer expected = new Integer(0);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
	
	@Test
	public void Should_Return_SubtotalOfZero_WhenQuantityLessThan1() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(50);
		product.setQualifier(3);
		product.setSpecialPrice(130);
		
		Integer quantity = new Integer(-1);
		Integer expected = new Integer(0);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
	
	@Test
	public void Should_Return_UnitPrice_WhenQuantityBelowSpecialPrice() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(50);
		product.setQualifier(3);
		product.setSpecialPrice(50);
		
		Integer quantity = new Integer(1);
		Integer expected = new Integer(50);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
	
	@Test
	public void Should_Return_SpecialPrice_WhenQuantityEqualsSpecialPrice() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(50);
		product.setQualifier(3);
		product.setSpecialPrice(130);
		
		Integer quantity = new Integer(3);
		Integer expected = new Integer(130);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
	
	@Test
	public void Should_Return_SpecialPricePlusUnitPrice_WhenQuantityGreaterThanSpecialPriceBy1() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(50);
		product.setQualifier(3);
		product.setSpecialPrice(130);
		
		Integer quantity = new Integer(4);
		Integer expected = new Integer(180);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
	
	@Test
	public void Should_Return_SpecialPricePlusUnitPrice_WhenMultipleQuantities() {
		Product product = new Product();
		product.setSku("A");
		product.setUnitPrice(50);
		product.setQualifier(3);
		product.setSpecialPrice(130);
		
		Integer quantity = new Integer(8);
		Integer expected = new Integer(360);
		Integer subtotal = quantifier.calculateSubtotal(product, quantity);
		assertEquals(expected, subtotal);
	}
}
