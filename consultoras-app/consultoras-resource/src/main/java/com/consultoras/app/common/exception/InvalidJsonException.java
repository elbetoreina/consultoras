package com.consultoras.app.common.exception;

public class InvalidJsonException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidJsonException(final String message){
		super (message);
	}
	
	public InvalidJsonException(final Throwable throwable){
		super(throwable);
	}

}
