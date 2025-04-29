package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoStore;
import com.alirizakaygusuz.dto.DtoStoreIU;

public interface IRestStoreController {

	RootEntity<DtoStore> saveStore(DtoStoreIU dtoStoreIU);
	
	RootEntity<DtoStore> findStoreById(Long id);
	
	RootEntity<List<DtoStore>> getAllStores();
	
	RootEntity<DtoStore> updateStore(DtoStoreIU dtoStoreIU,Long id);
	
	RootEntity<String> deleteStore(Long id);
}
