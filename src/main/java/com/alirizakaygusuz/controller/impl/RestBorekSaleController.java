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

import com.alirizakaygusuz.controller.IBorekSaleController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorekSaleIU;
import com.alirizakaygusuz.entity.DtoBorekSale;
import com.alirizakaygusuz.service.IBorekSaleService;

import jakarta.validation.Valid;

@RequestMapping("/rest/api/boreksale")
@RestController
public class RestBorekSaleController extends RestBaseController implements IBorekSaleController {

	@Autowired
	private IBorekSaleService borekSaleService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoBorekSale> saveBorekSale(@Valid @RequestBody DtoBorekSaleIU dtoBorekSaleIU) {
		return ok(borekSaleService.saveBorekSale(dtoBorekSaleIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoBorekSale> getBorekSaleById(@PathVariable Long id) {
		return ok(borekSaleService.getBorekSaleById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoBorekSale>> getBorekSales() {
		
		return ok(borekSaleService.getBorekSales());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoBorekSale> updateBorekSale(@Valid @RequestBody DtoBorekSaleIU dtoBorekSaleIU,@PathVariable Long id) {
		return ok(borekSaleService.updateBorekSale(dtoBorekSaleIU, id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteBorekSale(@PathVariable Long id) {
	
		borekSaleService.deleteBorekSale(id);
		return ok("Borek sale has been succesfully deleted");
	}

}
