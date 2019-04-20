package com.example.app.service;

import java.util.Collection;

import com.example.app.model.Product;

public interface ProductService {
	
	public void createProduct(Product product);
	public void updateProduct(String id, Product product);
	public void deleteProduct(String id);
	public Collection<Product> getProducts();

}
