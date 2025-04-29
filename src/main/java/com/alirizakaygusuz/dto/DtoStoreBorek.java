package com.alirizakaygusuz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoStoreBorek extends DtoBase {

	
	private DtoStore store;
	
	private DtoBorek borek;

}
