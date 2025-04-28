package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoAccountIU;

public interface IRestAccountController {
	RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

	RootEntity<DtoAccount> findAccountById(Long id);
	
	
	RootEntity<List<DtoAccount>> getAccounts();
	
	RootEntity<DtoAccount> updateAccount(DtoAccountIU dtoAccountIU , Long id);
	
	RootEntity<String> deletAccount(Long id);
}
