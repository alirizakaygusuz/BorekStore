package com.alirizakaygusuz.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.alirizakaygusuz.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Account extends BaseEntity {

	@OneToOne
    private Address address;
	
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "identity_number", length = 11, nullable = false, unique = true)
    private String identityNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "card_no", length = 16, nullable = false, unique = true)
    private String cardNo;

    @Column(name = "amount", precision = 19, scale = 4, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_type", length = 3, nullable = false)
    private CurrencyType currencyType;

    @Column(name = "bank_name", length = 100)
    private String bankName;

    @Column(name = "card_limit", precision = 19, scale = 4)
    private BigDecimal limitOfCard;
    
  

    
}
