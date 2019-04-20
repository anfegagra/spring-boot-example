package com.example.app.controller;

import java.util.Collection;

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

import com.example.app.model.Product;
import com.example.app.service.ProductService;

@RestController
@RequestMapping(value="/api")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value="/products")
	public ResponseEntity<Collection<Product>> getProducts() {
		logger.info("Se hace petición GET");
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}
	
	@PostMapping(value="/products")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		logger.info("Se hace petición POST");
		productService.createProduct(product);
		return new ResponseEntity<>("Product saved", HttpStatus.OK);
	}
	
	@PutMapping(value="/products/{id}")
	public ResponseEntity<String> saveProduct(@PathVariable("id") String id, @RequestBody Product product) {
		logger.info("Se hace petición PUT");
		productService.updateProduct(id, product);
		return new ResponseEntity<>("Product with id: " + product.getId() + " has been modified", HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
		logger.info("Se hace petición DELETE");
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product with id: " + id + " has been deleted", HttpStatus.OK);
	}
	
}
