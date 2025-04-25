package com.alirizakaygusuz.enums;

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

