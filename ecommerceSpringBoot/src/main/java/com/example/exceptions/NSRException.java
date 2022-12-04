package com.example.exceptions;

public class NSRException extends RuntimeException {

	/**
	 * No Such Receipt Exception
	 */
	private static final long serialVersionUID = 1L;
	
	public NSRException() {
		super("Receipt not in system");
	}

}
