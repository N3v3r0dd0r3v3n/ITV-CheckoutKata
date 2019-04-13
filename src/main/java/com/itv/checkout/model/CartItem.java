package com.itv.checkout.model;

public class CartItem {
	
	private String sku;
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public String toString() {
		return this.sku;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof CartItem) && (sku != null) ? 
				sku.equals(((CartItem) obj).sku) : (obj == this);
	}

	@Override
	public int hashCode() {
		return (sku != null) ? 
				(CartItem.class.hashCode() + sku.hashCode()) : super.hashCode();
	}	

}