package com.alirizakaygusuz.mapper;

import org.mapstruct.Mapper;

import com.alirizakaygusuz.dto.DtoBorekSale;
import com.alirizakaygusuz.entity.BorekSale;

@Mapper(componentModel  = "spring")
public interface BorekSaleMapper {
	
	DtoBorekSale borekSaleToDtoBorekSale(BorekSale borekSale);
	
	
	

}
