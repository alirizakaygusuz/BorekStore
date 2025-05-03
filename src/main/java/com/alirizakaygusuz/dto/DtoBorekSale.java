package com.alirizakaygusuz.dto;

import com.alirizakaygusuz.entity.Account;

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
	
	
	private Account account;


}
