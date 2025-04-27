package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;

public interface IRestBorekController {
	RootEntity<DtoBorek> saveBorek(DtoBorekIU dtoBorekIU);
	
	RootEntity<DtoBorek> findBorekById(Long id);
	
	RootEntity<List<DtoBorek>> getAllBoreks();
	
	RootEntity<DtoBorek> updateBorek(DtoBorekIU dtoBorekIU , Long id);
	
	RootEntity<String> deleteBorek(Long id);
}
