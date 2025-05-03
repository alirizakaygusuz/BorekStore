package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorekSale;
import com.alirizakaygusuz.dto.DtoBorekSaleIU;

public interface IBorekSaleController {

	RootEntity<DtoBorekSale> saveBorekSale(DtoBorekSaleIU dtoBorekSaleIU);
	
	RootEntity<DtoBorekSale> getBorekSaleById(Long id);
	
	RootEntity<List<DtoBorekSale>> getBorekSales();
	
	RootEntity<DtoBorekSale> updateBorekSale(DtoBorekSaleIU dtoBorekSaleIU , Long id);
	
	RootEntity<String> deleteBorekSale(Long id);
}
