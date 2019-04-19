package com.example.app.exception;

public class ProductNotfoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ProductNotfoundException(String mensaje) {
		super(mensaje);
	}
	
}
