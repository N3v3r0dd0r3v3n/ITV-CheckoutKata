package com.itv.checkout.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quantifier {
	
public Map<String, Integer> calculateQuantity(List<String> items) {
		
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
}
