package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoBorekSale;
import com.alirizakaygusuz.dto.DtoBorekSaleIU;

public interface IBorekSaleService {
	
	public DtoBorekSale saveBorekSale(DtoBorekSaleIU dtoBorekSaleIU);
	
	public DtoBorekSale getBorekSaleById(Long id);
	
	public List<DtoBorekSale> getBorekSales();
	
	public DtoBorekSale updateBorekSale(DtoBorekSaleIU dtoBorekSaleIU , Long id);
	
	public void deleteBorekSale(Long id);

}
