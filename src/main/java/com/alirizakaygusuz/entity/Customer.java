package com.alirizakaygusuz.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
	
    @Column(name = "customer_name", length = 50, nullable = false)
	private String customerName;
	
    
    @OneToMany 
	private Set<Account> accounts;
	
	
}
