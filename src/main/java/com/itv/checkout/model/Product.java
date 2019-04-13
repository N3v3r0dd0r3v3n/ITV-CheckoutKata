package com.itv.checkout.model;

public class Product {
	
	private char sku;

	public char getSku() {
		return sku;
	}

	public void setSku(char sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return String.format("%s sku: %s", super.toString(), this.sku);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}	

}
