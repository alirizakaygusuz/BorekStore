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

import com.alirizakaygusuz.controller.IRestStoreController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoStore;
import com.alirizakaygusuz.dto.DtoStoreIU;
import com.alirizakaygusuz.service.IStoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/store")
public class RestStoreController extends RestBaseController implements IRestStoreController{

	@Autowired
	private IStoreService storeService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoStore> saveStore(@Valid @RequestBody DtoStoreIU dtoStoreIU) {
		
		return ok(storeService.saveStore(dtoStoreIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoStore> findStoreById(@PathVariable Long id) {
		return ok(storeService.findStoreById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoStore>> getAllStores() {
		return ok(storeService.getAllStores());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoStore> updateStore(@Valid @RequestBody DtoStoreIU dtoStoreIU,@PathVariable Long id) {
		return ok(storeService.updateStore(dtoStoreIU, id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteStore(@PathVariable Long id) {
		storeService.deleteStore(id);
		
		return ok("Store has been succesfully deleted.");
	}

}
