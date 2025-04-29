package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;

public interface IRestStoreBorekController {
	RootEntity<DtoStoreBorek> saveStoreBorek(DtoStoreBorekIU dtoStoreBorekIU);
	
	RootEntity<DtoStoreBorek> findStoreBorekById(Long id);
	
	RootEntity<List<DtoStoreBorek>> getAllStoreBoreks();
	
	RootEntity<DtoStoreBorek> updateStoreBorek(DtoStoreBorekIU dtoStoreBorekIU,Long id);
	
	RootEntity<String> deleteStoreBorek(Long id);

}
