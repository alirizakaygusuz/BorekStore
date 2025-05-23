package com.alirizakaygusuz.exception;

import com.alirizakaygusuz.enums.BaseEnum;

import lombok.Getter;

@Getter
public enum ErrorType implements BaseEnum {

	// General Errors
	RECORD_DOES_NOT_EXIST("1000", "Record does not exist"),
	RECORD_NOT_FOUND("1001", "Record not found"),
	INVALID_INPUT("1002", "The provided input is invalid"), 
	ACCESS_DENIED("1003", "Access is denied"),
	USERNAME_OR_PASSWORD_INVALID("1005", "The username or password is incorrect"),
	USERNAME_NOT_FOUND("1006", "The username is not found"),
	USERNAME_IS_ALREADY_EXISTS("1007", "This username is already exists"),
	INVALID_BIRTH_DATE("1008", "Invalid birth date. You must be between 13 and 120 years old"),

	// Token Related Errors
	GENERAL_TOKEN_EXCEPTION("2000", "An error occurred while processing the token"),
	INVALID_TOKEN("2001", "The provided token is invalid"), 
	EXPIRED_TOKEN("2002", "The provided token has expired"),
	UNAUTHORIZED_ACCESS("20023", "Access is denied due to invalid credentials"),
	SIGNATURE_INVALID("2004", "The provided token's signature is invalid"),
	MALFORMED_TOKEN("2005", "The token format is malformed"),
	UNSUPPORTED_TOKEN("2006", "The provided token is unsupported"),
	REFRESH_TOKEN_DELETE_FAILED("2007", "Failed to delete reflesh token"),
	REFRESH_TOKEN_IS_NOT_FOUND("2008", "Refresh token is not found"),
	REFRESH_TOKEN_IS_ALREADY_EXPIRED("2009", "Refresh token is already expired"),
	REFRESH_TOKEN_ALREADY_USED("2010", "Refresh token is already used"),

	// AddressErrors
	ADDRESS_NOT_FOUND("3000", "Address is not found"),
	ADDRESS_IS_USED_BY_ACCOUNT("3001","The address is still in use by an account and cannot be deleted or updated."),

	// Borek Errors
	BOREK_NOT_FOUND("4000", "Borek is not found"),
	BOREK_ALREADY_SOLD("4001", "Borek is already sold"),
	BOREK_OUT_OF_STOCK("4002", "Borek is out of stock"),
	BOREK_NOT_IN_STORE("4003", "Borek is not store"),
	BOREK_CANNOT_BE_DELETED("4004", "This borek cannot be deleted because it has already been transferred to a store or purchased by a customer."),


	// Account Errors
	ACCOUNT_NOT_FOUND("5000", "Account is not found"), 
	ACCOUNT_ID_IS_MISSING("5001", "Account is not found"),
	ACCOUNT_ALREADY_ASSIGNED("5002", "Account is already assigned"),
	ACCOUNT_IDENTITYNUMBER_ALREADY_EXISTS("5003", "Identity number already exists in another account"),
	ACCOUNT_CARDNO_ALREADY_EXISTS("5004", "Card number already exists in another account"),
	ACCOUNT_IS_USED_BY_CUSTOMER_OR_STORE("5005", "This account is currently used by a customer or store and cannot be deleted."),
	// Customer Errors
	CUSTOMER_NOT_FOUND("6000", "Customer is not found"),
	CUSTOMER_ALREADY_BOUGHT_BOREK("6001", "Customer has purchased a börek and cannot be deleted."),

	STORE_NOT_FOUND("7000", "Store is not found"),
	STORE_CANNOT_BE_DELETED("7002", "Store cannot be deleted because it has already sold one or more böreks."),


	STOREBOREK_NOT_FOUND("8000", "StoreBorek is not found"),
	STOREBOREK_ALREADY_EXISTS("8001", "StoreBorek already exists"),
	STOREBOREK_BOREK_ALREADY_EXISTS("8002", "Borek already exists in another StoreBorek"),
	
	BOREKSALE_NOT_FOUND("9000", "Borek Sale is not found"),
	BOREKSALE__ALREADY_ASSIGNED("9001", "Borek Sale already assigned"),
	BOREKSALE_BOREK_ALREADY_EXISTS("9001", "Borek already sold"),
	
	
	INSUFFICIENT_FUNDS("10001", "Your card has insufficient funds"),
    INVALID_PAYMENT_METHOD("10002", "The payment method is invalid");


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
