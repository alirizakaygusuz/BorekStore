package com.alirizakaygusuz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

	@Column(name = "country", length = 50, nullable = false)
	public String country;

	@Column(name = "city", length = 50, nullable = true)
	public String city;

	@Column(name = "state", length = 50, nullable = true)
	public String state;

	@Column(name = "street", length = 100, nullable = true)
	public String street;

	@Column(name = "postal_code", length = 10, nullable = false)
	public String postalCode;

}
