package com.itv.checkout.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.itv.checkout.model.Product;
import com.itv.checkout.repository.ProductRepository;


@RunWith(MockitoJUnitRunner.class)
public class TestCheckoutService {
		
	@Mock
	CheckoutUtil mockCheckoutUtil;
	
	@Mock
	ProductRepository mockProductRepository;
	
	@InjectMocks
	CheckoutService checkoutService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void Should_Return_ZeroTotal_WhenSkuListIsNull() {
		Float expected = new Float(0);
		Float total = checkoutService.checkout(null);
		assertEquals(expected, total);
	}
	
	@Test
	public void Should_Return_ZeroTotal_WhenSkuListIsEmpty() {
		Float expected = new Float(0);
		Float total = checkoutService.checkout(new ArrayList<String>());
		assertEquals(expected, total);
	}
	
	@Test
	public void Should_Return_ZeroTotal_WhenNoProductsAvailable() {
		List<String> skus = new ArrayList<String>();
		skus.add("A");
		
		Map<String, Integer> quantities = new HashMap<String, Integer>();
		quantities.put("A", 1);
		when(mockCheckoutUtil.calculateQuantity(skus)).thenReturn(quantities);
		when(mockProductRepository.getProducts()).thenReturn(new HashMap<String, Product>());
		
		Float expected = new Float(0);
		Float total = checkoutService.checkout(skus);
		assertEquals(expected, total);
	}
	
	@Test
	public void Should_Return_Total_WhenProductsAvailable() {
		List<String> skus = new ArrayList<String>();
		skus.add("A");
		skus.add("B");
		
		Map<String, Integer> quantities = new HashMap<String, Integer>();
		quantities.put("A", 1);
		quantities.put("B", 1);
		
		Map<String, Product> products = new HashMap<String, Product>();
		Product productA = new Product();
		Product productB = new Product();
		products.put("A", productA);
		products.put("B", productB);
		
		when(mockCheckoutUtil.calculateQuantity(skus)).thenReturn(quantities);
		when(mockProductRepository.getProducts()).thenReturn(products);
		when(mockCheckoutUtil.calculateSubtotal(productA, 1)).thenReturn(5);
		when(mockCheckoutUtil.calculateSubtotal(productB, 1)).thenReturn(10);
		
		Float expected = new Float(0.15);
		Float total = checkoutService.checkout(skus);
		assertEquals(expected, total);
	}
	
	@Test
	public void Should_Return_Total_WhenProductUnavailable() {
		List<String> skus = new ArrayList<String>();
		skus.add("A");
		skus.add("B");
		skus.add("C");
		
		Map<String, Integer> quantities = new HashMap<String, Integer>();
		quantities.put("A", 1);
		quantities.put("B", 1);
		quantities.put("C", 1);
		
		Map<String, Product> products = new HashMap<String, Product>();
		Product productA = new Product();
		Product productB = new Product();
		products.put("A", productA);
		products.put("B", productB);
		
		when(mockCheckoutUtil.calculateQuantity(skus)).thenReturn(quantities);
		when(mockProductRepository.getProducts()).thenReturn(products);
		when(mockCheckoutUtil.calculateSubtotal(productA, 1)).thenReturn(5);
		when(mockCheckoutUtil.calculateSubtotal(productB, 1)).thenReturn(10);
		
		Float expected = new Float(0.15);
		Float total = checkoutService.checkout(skus);
		assertEquals(expected, total);
	}
}
