package com.example.app.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.app.exception.ProductNotfoundException;
import com.example.app.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static Map<String, Product> products = new HashMap<>();
	
	static {
		Product nike = new Product();
		nike.setId("1");
		nike.setName("nike");
		products.put(nike.getId(), nike);
		
		Product adidas = new Product();
		adidas.setId("2");
		adidas.setName("adidas");
		products.put(adidas.getId(), adidas);
	}

	@Override
	public void createProduct(Product product) {
		products.put(product.getId(), product);
	}

	@Override
	public void updateProduct(String id, Product product) {
		if(!products.containsKey(id)) throw new ProductNotfoundException("Producto no encontrado");
		products.remove(id);
		product.setId(id);
		products.put(id, product);
	}

	@Override
	public void deleteProduct(String id) {
		products.remove(id);
	}

	@Override
	public Collection<Product> getProducts() {
		return products.values();
	}

}
