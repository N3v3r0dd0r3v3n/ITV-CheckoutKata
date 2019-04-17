package com.itv.checkout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itv.checkout.model.Product;

@Service
public class CheckoutUtil {
	
	Logger logger = LogManager.getLogger(CheckoutUtil.class);
	
	public Map<String, Integer> calculateQuantity(List<String> items) {
		
		logger.info(String.format("CheckoutUtil.calculateQuantity: items[%s]",items));
		Map<String, Integer> productTotals = new HashMap<String, Integer>();
	
		if (items != null) {
			for (String item: items) {
				Integer itemQuantity = productTotals.get(item);
					
				if (itemQuantity == null) {
					itemQuantity = new Integer(1);
				} else {
					itemQuantity = itemQuantity + 1;
				}
				productTotals.put(item, itemQuantity);
			}
		}	
		return productTotals;
	}
	
	public Integer calculateSubtotal(Product product, Integer quantity) {
		logger.info(String.format("CheckoutUtil.calculateSubtotal: product[%s] quantity[%s]", product, quantity));
		Integer subtotal = new Integer(0);
		
		if (product != null && quantity != null && quantity > 0) {
			
			int quotient = 0;
			int remainder = 0;
			
			if (product.getQualifier() > 0) {
				quotient = quantity / product.getQualifier(); 
				remainder = quantity % product.getQualifier();
			}
			
			if (remainder != 0) {
				subtotal = subtotal + product.getUnitPrice() * remainder;
			}
	        
			if (quotient != 0) {
				subtotal = subtotal + product.getSpecialPrice()* quotient;
			}
	        
	        logger.debug(String.format("%s at regular price of %s", remainder, product.getUnitPrice().toString()));
	        logger.debug(String.format("%s at special price of %s", quotient, product.getSpecialPrice().toString()));
	        logger.info(String.format("SKU %s subtotal %d",  product.getSku(), subtotal));
		}
        return subtotal;
	}
	
}
