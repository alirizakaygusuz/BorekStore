package com.alirizakaygusuz.exception;

import com.alirizakaygusuz.enums.BaseEnum;

import lombok.Getter;

@Getter
public enum ErrorType implements BaseEnum{
	
	// General Errors
    RECORD_DOES_NOT_EXIST("1000", "Record does not exist"),
    RECORD_NOT_FOUND("1001", "Record not found"),
    INVALID_INPUT("1002", "The provided input is invalid"),
    ACCESS_DENIED("1003", "Access is denied"),
    USERNAME_OR_PASSWORD_INVALID("1005","The username or password is incorrect"),
    USERNAME_NOT_FOUND("1006","The username is not found"),
    USERNAME_IS_ALREADY_EXISTS("1007","This username is already exists"),

    // Token Related Errors
    GENERAL_TOKEN_EXCEPTION("2000", "An error occurred while processing the token"),
    INVALID_TOKEN("2001", "The provided token is invalid"),
    EXPIRED_TOKEN("2002", "The provided token has expired"),
    UNAUTHORIZED_ACCESS("20023", "Access is denied due to invalid credentials"),
    SIGNATURE_INVALID("2004", "The provided token's signature is invalid"),
    MALFORMED_TOKEN("2005", "The token format is malformed"),
    UNSUPPORTED_TOKEN("2006", "The provided token is unsupported"),
    REFRESH_TOKEN_DELETE_FAILED("2007","Failed to delete reflesh token"),
    REFRESH_TOKEN_IS_NOT_FOUND("2008","Refresh token is not found"),
    REFRESH_TOKEN_IS_ALREADY_EXPIRED("2009","Refresh token is already expired"),
    REFRESH_TOKEN_ALREADY_USED("2010","Refresh token is already used"),
	
    
    //AddressErrors
    ADDRESS_NOT_FOUND("3000","Address is not found"),
	
	//Borek Errors
   BOREK_NOT_FOUND("4000","Borek is not found");


    private final String code;
    private final String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

 

	@Override
	public String getDisplayName() {
		return message;
	}
}
