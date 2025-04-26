package com.alirizakaygusuz.exception;

import com.alirizakaygusuz.enums.BaseEnum;

import lombok.Getter;

@Getter
public enum ErrorType implements BaseEnum{
	RECORD_DOES_NOT_EXIST("1000", "RECORD DOES NOT EXIST"),
    RECORD_NOT_FOUND("1001", "RECORD NOT FOUND"),
    INVALID_INPUT("1002", "THE PROVIDED INPUT IS INVALID"),
    ACCESS_DENIED("1003", "ACCESS DENIED");

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
