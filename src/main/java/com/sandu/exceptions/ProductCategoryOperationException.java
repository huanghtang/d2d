package com.sandu.exceptions;

public class ProductCategoryOperationException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3340497605148343712L;

	public ProductCategoryOperationException(String msg) {
		super(msg); 
	}
}
