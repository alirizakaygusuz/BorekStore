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
@Table(name = "store_borek"
,uniqueConstraints = {@UniqueConstraint(columnNames = {"store_id","borek_id"},name = "uq_store_borek")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreBorek extends BaseEntity {
	
	@ManyToOne
	private Store store;
	
	@ManyToOne
	private Borek borek;

}
