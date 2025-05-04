package com.alirizakaygusuz.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alirizakaygusuz.entity.Address;
import com.alirizakaygusuz.enums.CurrencyType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detailed account data used in account-related responses and requests")
public class DtoAccount extends DtoBase {

    @Schema(description = "Address information of the account holder")
    private DtoAddress address;

    @Schema(description = "First name of the account holder", example = "Ali")
    private String firstName;

    @Schema(description = "Last name of the account holder", example = "Kaygusuz")
    private String lastName;

    @Schema(description = "Turkish identity number (11 digits)", example = "12345678901")
    private String identityNumber;

    @Schema(description = "Date of birth", example = "1995-08-15")
    private LocalDate birthDate;

    @Schema(description = "16-digit card number", example = "1234567812345678")
    private String cardNo;

    @Schema(description = "Available account balance", example = "10000.00")
    private BigDecimal amount;

    @Schema(description = "Currency type of the account", example = "USD")
    private CurrencyType currencyType;

    @Schema(description = "Name of the associated bank", example = "Ziraat Bankası")
    private String bankName;

    @Schema(description = "Credit card limit", example = "15000.00")
    private BigDecimal limitOfCard;
}

