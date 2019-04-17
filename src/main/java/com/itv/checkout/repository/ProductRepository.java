package com.itv.checkout.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itv.checkout.model.Product;

@Repository
public class ProductRepository {
	
	Logger logger = LogManager.getLogger(ProductRepository.class);
	
	@Autowired
	DateUtil dateUtil;
	
	private Set<ProductSet> productSets = new HashSet<ProductSet>();
	
	public ProductRepository() {
		super();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 2);
		Date endDate = calendar.getTime();
		calendar.add(Calendar.DATE, -6);
		Date startDate = calendar.getTime();
		productSets.add(buildActiveSet(startDate, endDate));
		endDate = startDate;
		calendar.add(Calendar.DATE, -6);
		startDate = calendar.getTime();
		productSets.add(buildArchiveSet(startDate, endDate));
	}
	
	public Map<String, Product> getProducts() {
		Date today = dateUtil.getCurrentDate();
		logger.info(String.format("Getting products for %s", today));
		Map<String, Product> products  = new HashMap<String, Product>();
		for (ProductSet productSet: productSets) {
			if (!productSet.startDate.after(today) && !productSet.endDate.before(today)) {
				products = productSet.getProducts();
				break;
			}
		}
		return products;
	}
	
	private Product buildProduct(String sku, Integer unitPrice) {
		return this.buildProduct(sku, unitPrice, 0, 0);
	}
	
	private Product buildProduct(String sku, Integer unitPrice, Integer qualifier, Integer specialPrice) {
		Product product = new Product();
		product.setSku(sku);
		product.setUnitPrice(unitPrice);
		product.setQualifier(qualifier);
		product.setSpecialPrice(specialPrice);
		return product;
	}
	
	private class ProductSet {
		private Date startDate;
		private Date endDate;
		private Map<String, Product> products;
		
		public ProductSet(Date startDate, Date endDate, Map<String, Product> products) {
			super();
			this.startDate = startDate;
			this.endDate = endDate;
			this.products = products;
		}

		public Map<String, Product> getProducts() {
			return products;
		}
	}
	
	private ProductSet buildActiveSet(Date startDate, Date endDate) {
		Map<String, Product> products = new HashMap<String, Product>();
		products.put("A", this.buildProduct("A", 50, 3, 130));
		products.put("B", this.buildProduct("B", 30, 2, 45));
		products.put("C", this.buildProduct("C", 20));
		products.put("D", this.buildProduct("D", 15));
		
		ProductSet productSet = new ProductSet(startDate, endDate, products);
		return productSet;
	}
	
	private ProductSet buildArchiveSet(Date startDate, Date endDate) {
		Map<String, Product> products = new HashMap<String, Product>();
		products.put("A", this.buildProduct("A", 45, 3, 110));
		products.put("B", this.buildProduct("B", 25, 2, 40));
		products.put("D", this.buildProduct("D", 15));
		ProductSet productSet = new ProductSet(startDate, endDate, products);
		return productSet;
	}

}
