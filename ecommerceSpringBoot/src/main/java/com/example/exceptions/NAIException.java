package com.example.exceptions;

public class NAIException extends RuntimeException {

	/**
	 * Not an Item Exception
	 */
	private static final long serialVersionUID = 1L;
	public NAIException() {
		super("Could not find item.");
	}

}
