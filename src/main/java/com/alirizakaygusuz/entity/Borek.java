package com.alirizakaygusuz.entity;

import java.math.BigDecimal;

import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.enums.BorekType;
import com.alirizakaygusuz.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "borek")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borek extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private BorekType borekType;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private final CurrencyType currencyType = CurrencyType.AUD; 

    @Enumerated(EnumType.STRING)
    private BorekStatus borekStatus;

}

