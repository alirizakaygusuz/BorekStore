package com.alirizakaygusuz.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestBorekController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;
import com.alirizakaygusuz.service.IBorekService;

@RestController
@RequestMapping("/rest/api/borek")
public class RestBorekController extends RestBaseController implements IRestBorekController {

	@Autowired
	private IBorekService borekService;

	@Override
	public RootEntity<DtoBorek> saveBorek(DtoBorekIU dtoBorekIU) {
		return ok(borekService.saveBorek(dtoBorekIU));
	}

	@Override
	public RootEntity<DtoBorek> findBorekById(Long id) {
		
		return ok(borekService.findBorekById(id));
	}

	@Override
	public RootEntity<List<DtoBorek>> getAllBoreks() {
		
		return ok(borekService.getAllBoreks());
	}

	@Override
	public RootEntity<DtoBorek> updateBorek(DtoBorekIU dtoBorekIU, Long id) {
		
		return ok(borekService.updateBorek(dtoBorekIU, id));
	}

	@Override
	public RootEntity<String> deleteBorek(Long id) {
		borekService.deleteBorek(id);
		return ok("Borek deleted succesfully");
	}
	
	
	
}
