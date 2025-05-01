package com.alirizakaygusuz.mapper;

import org.mapstruct.Mapper;

import com.alirizakaygusuz.entity.BorekSale;
import com.alirizakaygusuz.entity.DtoBorekSale;

@Mapper(componentModel  = "spring")
public interface BorekSaleMapper {
	
	DtoBorekSale borekSaleToDtoBorekSale(BorekSale borekSale);
	
	
	

}
