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

import com.alirizakaygusuz.controller.IRestCustomerController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoCustomer;
import com.alirizakaygusuz.dto.DtoCustomerIU;
import com.alirizakaygusuz.service.ICustomerService;

import jakarta.validation.Valid;

@RequestMapping("/rest/api/customer")
@RestController
public class RestCustomerController extends RestBaseController implements IRestCustomerController {
	
	@Autowired
	private ICustomerService customerService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoCustomer> findCustomerById(@PathVariable Long id) {

		return ok(customerService.findCustomerById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCustomer>> getAllCustomers() {

		return ok(customerService.getAllCustomers());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCustomer> updateCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU,@PathVariable Long id) {

		return ok(customerService.updateCustomer(dtoCustomerIU, id));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteCustomer(@PathVariable Long id) {

		customerService.deleteCustomer(id);
		return ok("Customer has been successfully deleted.");
	}

}
