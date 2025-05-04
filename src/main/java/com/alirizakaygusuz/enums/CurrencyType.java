package com.alirizakaygusuz.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Currency types: TRY (Turkish Lira), USD (US Dollar), EUR (Euro), AUD (Australian Dollar), NZD (New Zealand Dollar)")
public enum CurrencyType implements BaseEnum {
    TRY("TURKISH LIRA"),
    USD("UNITED STATES DOLLAR"),
    AUD("AUSTRALIAN DOLLAR"),
    EUR("EURO"),
    NZD("NEW ZEALAND DOLLAR");

    private final String displayName;

    CurrencyType(String displayName) {
        this.displayName = displayName;
    }

 
	@Override
	public String getDisplayName() {
		
		return displayName;
	}
}

