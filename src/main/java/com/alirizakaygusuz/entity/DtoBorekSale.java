package com.alirizakaygusuz.entity;

import com.alirizakaygusuz.dto.DtoBase;
import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoCustomer;
import com.alirizakaygusuz.dto.DtoStore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBorekSale extends DtoBase {

	private DtoStore store;

	private DtoBorek borek;

	private DtoCustomer customer;
	



}
