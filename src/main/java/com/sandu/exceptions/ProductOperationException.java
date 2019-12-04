package com.sandu.exceptions;

public class ProductOperationException extends RuntimeException{
	private static final long serialVersionUID = -6190392479891373602L;


	public ProductOperationException(String msg) {
		super(msg); 
	}
}
