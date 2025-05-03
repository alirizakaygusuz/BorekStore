package com.alirizakaygusuz.enums;

public enum BorekStatus implements BaseEnum{
   
	SALABLE("SALABLE"),
	SOLD("SOLD"),
    OUT_OF_STOCK("OUT OF STOCK");
    
    

    private final String displayName;

    BorekStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}