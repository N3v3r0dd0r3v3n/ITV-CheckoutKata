package com.itv.checkout.service;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.checkout.model.Product;
import com.itv.checkout.repository.DateUtil;
import com.itv.checkout.repository.ProductRepository;

@Service
public class CheckoutService {
	
	Logger logger = LogManager.getLogger(CheckoutService.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CheckoutUtil quantifier;

	public Float checkout(List<String> items) {
		logger.info(String.format("CheckoutService.checkout: items %s", items));
		Float total = new Float(0);

		if (items != null && !items.isEmpty()) {
			Map<String, Integer> skuQuantities = quantifier.calculateQuantity(items);
			
			Map<String, Product> pricePoints = productRepository.getProducts();
			
			for (Map.Entry<String, Integer> skuQuantity : skuQuantities.entrySet()) {
			    String sku = skuQuantity.getKey();
			    Integer quantity = skuQuantity.getValue();
			    
			    Product product = pricePoints.get(sku);
			    if (product == null) {
			    	logger.error(String.format("No price point found for sku: %s", sku));
			    } else {
			    	logger.info(String.format("Price point found for sku: %s", sku));
			    	Integer subtotal = quantifier.calculateSubtotal(product, quantity);
			    	total = total + subtotal;
			    }
			}		
		}
		total = total / 100;
		logger.info(String.format("Product totals: %s", total.toString()));
		return total;
	}
	
}