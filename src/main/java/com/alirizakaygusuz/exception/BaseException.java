package com.alirizakaygusuz.exception;

public class BaseException extends RuntimeException{
	
	
	public BaseException(ErrorMessage errorMessage) {
		super(errorMessage.buildErrorMessage());
	}
	
}
