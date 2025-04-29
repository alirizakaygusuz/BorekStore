package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoStore;
import com.alirizakaygusuz.dto.DtoStoreIU;
import com.alirizakaygusuz.entity.Store;

public interface IStoreService {
	
	public DtoStore saveStore(DtoStoreIU dtoStoreIU);
	
	public DtoStore findStoreById(Long id);
	
	public List<DtoStore> getAllStores();
	
	public DtoStore updateStore(DtoStoreIU dtoStoreIU , Long id);
	
	
	public void deleteStore(Long id);
	
	public Store findStoreByIdThrow(Long id);

}
