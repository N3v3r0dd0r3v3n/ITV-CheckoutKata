package com.itv.checkout.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.checkout.model.CartItem;
import com.itv.checkout.model.Product;
import com.itv.checkout.repository.ProductRepository;

@Service
public class CheckoutService {
	
	Logger logger = LogManager.getLogger(CheckoutService.class);
	
	@Autowired
	ProductRepository productRepository;
	
	public BigDecimal checkout(List<CartItem> items) {
		logger.info(String.format("CheckoutService.checkout: items[%s]", items.toString()));
		BigDecimal total = new BigDecimal(0);
		
		Map<String, Integer> cartItems = this.calculateQuantity(items);
		//TODO Get price points based on date?
		
		Map<String, Product> pricePoints = productRepository.getProducts();
		
		for (Map.Entry<String, Integer> cartItem : cartItems.entrySet()) {
		    String cartSku = cartItem.getKey();
		    Integer quantity = cartItem.getValue();
		    
		    Product product = pricePoints.get(cartSku);
		    if (product == null) {
		    	logger.error(String.format("No price point found for sku: %s", cartSku));
		    } else {
		    	logger.info(String.format("Price point found for sku: %s", cartSku));
		    	BigDecimal subtotal = calculateSubtotal(product, quantity);
		    	total = total.add(subtotal);
		    }
		}
	
		logger.info(String.format("Product totals: %s", total.toString()));
		return total;
	}
	
	//TODO Is this ok?  Separate class?
	private BigDecimal calculateSubtotal(Product product, Integer quantity) {
		logger.info(String.format("Product %s Quantity %s", product.toString(), quantity));
		BigDecimal subtotal = new BigDecimal(0);
		
		int quotient = quantity / product.getQualifier(); 
		int remainder = quantity % product.getQualifier();
		
		if (remainder != 0) {
			subtotal = product.getUnitPrice().multiply(new BigDecimal(remainder));
			//subtotal = subtotal + (remainder * product.getUnitPrice().doubleValue());
		}
        
		if (quotient != 0) {
			subtotal = product.getSpecialPrice().multiply(new BigDecimal(quotient));
			//subtotal = subtotal + (quotient * product.getSpecialPrice().doubleValue());
		}
        
        logger.info(String.format("%s at regular price of %s", remainder, product.getUnitPrice().toString()));
        logger.info(String.format("%s at special price of %s", quotient, product.getSpecialPrice().toString()));
        logger.info(String.format("Subtotal %f",  subtotal));
        return subtotal;
	}
	
	private Map<String, Integer> calculateQuantity(List<CartItem> items) {
		
		Map<String, Integer> productTotals = new HashMap<String, Integer>();
		
		for (CartItem item: items) {
			Integer itemQuantity = productTotals.get(item.getSku());
			
			if (itemQuantity == null) {
				itemQuantity = new Integer(1);
			} else {
				itemQuantity = itemQuantity + 1;
			}
			productTotals.put(item.getSku().toString(), itemQuantity);
		}
		return productTotals;
	}
	
}