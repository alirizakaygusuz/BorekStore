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

import com.alirizakaygusuz.controller.IRestAccountController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoAccountIU;
import com.alirizakaygusuz.service.IAccountService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController extends RestBaseController implements IRestAccountController{

	
	@Autowired
	private IAccountService accountService;
	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {

		return ok(accountService.saveAccount(dtoAccountIU));
	}


	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoAccount> findAccountById(@PathVariable Long id) {
		
		return ok(accountService.findAccountById(id));
	}


	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAccount>> getAccounts() {
		
		return ok(accountService.getAccounts());
	}


	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU,@PathVariable Long id) {
		
		return ok(accountService.updateAccount(dtoAccountIU, id));
	}


	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deletAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ok("Account has been deleted succesfully");
	}

}
