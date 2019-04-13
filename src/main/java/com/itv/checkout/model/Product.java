package com.itv.checkout.model;

import java.math.BigDecimal;

public class Product {
	
	private String sku;
	private BigDecimal unitPrice;
	private Integer qualifier;
	private BigDecimal specialPrice;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getQualifier() {
		return qualifier;
	}

	public void setQualifier(Integer qualifier) {
		this.qualifier = qualifier;
	}

	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public String toString() {
		return String.format("sku: %s | Unit Price: %s | Qualifier: %s | Special Price: %s", this.sku, this.unitPrice.toString(), this.qualifier.toString(), this.specialPrice.toString());
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Product) && (sku != null) ? 
				sku.equals(((Product) obj).sku) : (obj == this);
	}

	@Override
	public int hashCode() {
		return (sku != null) ? 
				(Product.class.hashCode() + sku.hashCode()) : super.hashCode();
	}	

}