package com.alirizakaygusuz.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomer extends DtoBase {
	private String customerName;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<DtoAccount> accounts=new HashSet<DtoAccount>();

}
