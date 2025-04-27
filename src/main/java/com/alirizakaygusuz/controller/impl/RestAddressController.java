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

import com.alirizakaygusuz.controller.IRestAddressController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;
import com.alirizakaygusuz.service.IAddressService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/rest/api/address")
public class RestAddressController extends RestBaseController implements IRestAddressController {

	
	@Autowired
	private IAddressService addressService;
	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		
		return ok(addressService.saveAddress(dtoAddressIU));
	}


	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoAddress> findAddressById(@PathVariable Long id) {
		
		return ok(addressService.findAddressById(id));
	}


	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAddress>> getAllAddresses() {

		return ok(addressService.getAllAddresses());
	}


	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoAddress> updateAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU,@PathVariable Long id) {
		return ok(addressService.updateAddress(dtoAddressIU, id));
	}


	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return ok("Address deleted successfully.");
	}

}
