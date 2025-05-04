package com.alirizakaygusuz.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = """
Types of börek:
- CHEESE_AND_SPINACH → Cheese and Spinach
- PLAIN_CHEESE → Plain Cheese
- LAMB_AND_CHEESE → Lamb and Cheese
- SPICY_POTATO → Spicy Potato
- CHICKEN_AND_MUSHROOM → Chicken and Mushroom
""")
public enum BorekType implements BaseEnum {

    CHEESE_AND_SPINACH("Cheese and Spinach"),
    PLAIN_CHEESE("Plain Cheese"),
    LAMB_AND_CHEESE("Lamb and Cheese"),
    SPICY_POTATO("Spicy Potato"),
    CHICKEN_AND_MUSHROOM("Chicken and Mushroom");

    private final String displayName;

    BorekType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


