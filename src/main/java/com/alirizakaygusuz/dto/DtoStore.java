package com.alirizakaygusuz.dto;

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

	private DtoAddress address;

	private DtoAccount account;
}
