package com.alirizakaygusuz.dto;

import com.alirizakaygusuz.entity.Account;
import com.alirizakaygusuz.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStore extends DtoBase {

	private String name;

	private Address address;

	private Account account;
}
