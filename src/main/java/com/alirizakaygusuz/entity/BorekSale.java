package com.alirizakaygusuz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "borek_sale"
, uniqueConstraints = {@UniqueConstraint(columnNames = {"store_id" , "borek_id" , "customer_id"},name = "uq_store_borek_customer")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorekSale extends BaseEntity {
	
	@ManyToOne
	private Store store;
	
	@ManyToOne
	private Borek borek;

	@ManyToOne
	private Customer customer;

}
