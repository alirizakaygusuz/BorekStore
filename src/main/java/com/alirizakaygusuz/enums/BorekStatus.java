package com.alirizakaygusuz.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = """
Represents the sales status of a börek item:
- SALABLE → The item has not been purchased yet. It is available for the store to buy.
- SOLD → The item has been purchased by the store, but not yet by the customer.
- OUT_OF_STOCK → The item has already been purchased by a customer and is no longer available.
""")

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