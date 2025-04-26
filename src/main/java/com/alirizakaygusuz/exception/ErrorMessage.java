package com.alirizakaygusuz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	private ErrorType errorType;

	private String additionalInfo;

	public String buildErrorMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(errorType.getCode() + " " + errorType.getMessage());

		if (this.additionalInfo != null) {
			builder.append(":" + additionalInfo);
		}
		return builder.toString();
	}

}
