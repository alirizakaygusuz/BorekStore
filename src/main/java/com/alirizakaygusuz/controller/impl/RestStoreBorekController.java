package com.alirizakaygusuz.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestStoreBorekController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;
import com.alirizakaygusuz.service.IStoreBorekService;

import jakarta.validation.Valid;

@RequestMapping("/rest/api/storeborek")
@RestController
public class RestStoreBorekController extends RestBaseController implements IRestStoreBorekController{

	@Autowired
	private IStoreBorekService storeBorekService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoStoreBorek> saveStoreBorek(@Valid @RequestBody DtoStoreBorekIU dtoStoreBorekIU) {
	
		return ok(storeBorekService.saveBorek(dtoStoreBorekIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoStoreBorek> findStoreBorekById(@PathVariable Long id) {
		
		return ok(storeBorekService.findBorekById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoStoreBorek>> getAllStoreBoreks() {
		
		return ok(storeBorekService.getAllBoreks());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoStoreBorek> updateStoreBorek(@Valid @RequestBody DtoStoreBorekIU dtoStoreBorekIU,@PathVariable Long id) {
		
		return ok(storeBorekService.updateBorek(dtoStoreBorekIU, id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteStoreBorek(@PathVariable Long id) {
		storeBorekService.deleteBorek(id);
		
		return ok("StoreBorek has been deleted.");
		
		
	}
	
	
}
