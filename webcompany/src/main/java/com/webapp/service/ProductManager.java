package com.webapp.service;

import java.util.List;

import com.webapp.entity.Product;

public interface ProductManager {
	public Product getById(int id);
	public List<Product> showForPage(final int offset, final int length);
	public List<Product> getIndexProduct();
	public int getAllCount();
	public void deleteById(int id);
	public int add(Product product);
	public int update(Product product);
}
