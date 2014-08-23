package com.webapp.action;

import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;
import com.webapp.entity.Product;
import com.webapp.service.ProductManager;
import com.webapp.util.Page;
import com.webapp.util.PageUtil;

public class ProductAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private int id;
	private int currentPage;
	private Page page;
	private Product product;
	private List<Product> listProduct;
	private ProductManager prodcutManager;
	public ProductAction(){
		this.id = 6;
		this.currentPage = 1;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getListProduct() {
		return listProduct;
	}
	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	public ProductManager getProdcutManager() {
		return prodcutManager;
	}
	@Resource
	public void setProdcutManager(ProductManager prodcutManager) {
		this.prodcutManager = prodcutManager;
	}
	public String execute(){
		int totalCount = prodcutManager.getAllCount();
		page = PageUtil.createPage(id, currentPage, totalCount);
		listProduct = prodcutManager.showForPage(page.getBeginIndex(), page.getPageSize());
		return SUCCESS;
	}
	public String getById(){
		product = prodcutManager.getById(id);
		return "product";
	}
	public String getIndexProduct(){
		listProduct = prodcutManager.getIndexProduct();
		return SUCCESS;
	}
	public String manager(){
		this.execute();
		return SUCCESS;
	}
	public String add(){
		prodcutManager.add(product);
		this.execute();
		return SUCCESS;
	}
	public String delete(){
		prodcutManager.deleteById(product.getId());
		this.execute();
		return SUCCESS;
	}
	public String update(){
		this.add();
		return SUCCESS;
	}
	public String managerUpdate(){
		this.getById();
		return "manager";
	}
}
