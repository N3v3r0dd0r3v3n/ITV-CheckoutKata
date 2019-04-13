package com.itv.checkout.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<String> sku = new ArrayList<String>();

	public List<String> getSku() {
		return sku;
	}

	public void setSku(List<String> sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sku.toString();
	}
	
}
