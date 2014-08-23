package com.webapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.webapp.entity.Product;
import com.webapp.service.ProductManager;

@Component("productManager")
public class ProductManagerImpl implements ProductManager {

//	private ProductDao productDao;
	public int add(Product product) {
		return 1;
	}
	public void deleteById(int id) {
	}
	public int getAllCount() {
//		return productDao.getAllCount();
		return 1;
	}
	public Product getById(int id) {
//		return productDao.loadById(id);
		return null;
	}
//	@Resource
//	public void setProductDao(ProductDao productDao) {
//		this.productDao = productDao;
//	}
	public List<Product> showForPage(int offset, int length) {
//		return productDao.showForPage(offset, length);
		return null;
	}
	public int update(Product product) {
		return 0;
	}
	public List<Product> getIndexProduct() {
//		return productDao.loadIndexProduct();
		return null;
	}
}
