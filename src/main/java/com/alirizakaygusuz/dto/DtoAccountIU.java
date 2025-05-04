package com.alirizakaygusuz.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alirizakaygusuz.enums.CurrencyType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input DTO for creating or updating an account. Used in POST/PUT requests.")
public class DtoAccountIU {

    @Schema(description = "ID of the associated address", example = "1", required = true)
    @NotNull(message = "Address ID cannot be null")
    private Long address_id;

    @Schema(description = "First name of the account holder", example = "Ali", required = true)
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Schema(description = "Last name of the account holder", example = "Kaygusuz", required = true)
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Schema(description = "National identity number (11 digits)", example = "12345678901", required = true)
    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;

    @Schema(description = "Date of birth (YYYY-MM-DD)", example = "1995-08-15", required = true)
    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;

    @Schema(description = "Credit card number (16 digits)", example = "1234567812345678", required = true)
    @NotBlank(message = "Card number cannot be blank")
    private String cardNo;

    @Schema(description = "Account balance", example = "10000.00", required = true)
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    @Schema(description = "Currency type", example = "TRY", required = true)
    @NotNull(message = "Currency type cannot be null")
    private CurrencyType currencyType;

    @Schema(description = "Bank name", example = "Ziraat Bankası", required = true)
    @NotBlank(message = "Bank name cannot be blank")
    private String bankName;

    @Schema(description = "Card limit", example = "15000.00", required = true)
    @NotNull(message = "Limit of card cannot be null")
    private BigDecimal limitOfCard;
}
