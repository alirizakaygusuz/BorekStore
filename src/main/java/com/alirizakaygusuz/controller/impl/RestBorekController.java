package com.alirizakaygusuz.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestBorekController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;
import com.alirizakaygusuz.service.IBorekService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/borek")
public class RestBorekController extends RestBaseController implements IRestBorekController {

	@Autowired
	private IBorekService borekService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoBorek> saveBorek(@Valid @RequestBody DtoBorekIU dtoBorekIU) {
		return ok(borekService.saveBorek(dtoBorekIU));
	}
	
	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoBorek> findBorekById(@Valid @PathVariable Long id) {
		
		return ok(borekService.findBorekById(id));
	}
	
	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoBorek>> getAllBoreks() {
		
		return ok(borekService.getAllBoreks());
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoBorek> updateBorek(@Valid @RequestBody DtoBorekIU dtoBorekIU,@Valid  @PathVariable Long id) {
		
		return ok(borekService.updateBorek(dtoBorekIU, id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteBorek(@Valid  @PathVariable Long id) {
		borekService.deleteBorek(id);
		return ok("Borek deleted succesfully");
	}
	
	
	
}
