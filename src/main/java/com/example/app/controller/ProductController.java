package com.example.app.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.exception.ProductNotfoundException;
import com.example.app.model.Product;

@RestController
@RequestMapping(value="/app")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

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
	
	@GetMapping(value="/products")
	public ResponseEntity<Collection<Product>> getProducts() {
		logger.info("Se hace petición GET");
		return new ResponseEntity<>(products.values(), HttpStatus.OK);
	}
	
	@PostMapping(value="/products")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		logger.info("Se hace petición POST");
		products.put(product.getId(), product);
		return new ResponseEntity<>("Product saved", HttpStatus.OK);
	}
	
	@PutMapping(value="/products/{id}")
	public ResponseEntity<String> saveProduct(@PathVariable("id") String id, @RequestBody Product product) {
		if(!products.containsKey(id)) throw new ProductNotfoundException("Producto no encontrado");
		logger.info("Se hace petición PUT");
		products.remove(id);
		product.setId(id);
		products.put(id, product);
		return new ResponseEntity<>("Product with id: " + product.getId() + " has been modified", HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
		logger.info("Se hace petición DELETE");
		products.remove(id);
		return new ResponseEntity<>("Product with id: " + id + " has been deleted", HttpStatus.OK);
	}
	
}
