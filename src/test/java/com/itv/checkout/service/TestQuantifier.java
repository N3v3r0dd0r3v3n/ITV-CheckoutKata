package com.itv.checkout.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.isA;

public class TestQuantifier {
	
	private final String FIRST_SKU = "A";
	private final String SECOND_SKU = "B";
	
	@Test
	public void Should_Return_EmptyMap_WhenSkuListIsEmpty() {
		Quantifier quantifier = new Quantifier();
		Map<String, Integer> results = quantifier.calculateQuantity(new ArrayList<String>());
		assertTrue(results.isEmpty());
	}

	@Test
	public void Should_Return_EmptyMap_WhenSkuListIsNull() {
		Quantifier quantifier = new Quantifier();
		Map<String, Integer> results = quantifier.calculateQuantity(null);
		assertTrue(results.isEmpty());
	}
	
	@Test 
	public void Should_Return_TwoEntries_WhenTwoSkusSupplied() {
		Quantifier quantifier = new Quantifier();
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
		Quantifier quantifier = new Quantifier();
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
		Quantifier quantifier = new Quantifier();
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
}
