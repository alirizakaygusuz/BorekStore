package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoAccountIU;

public interface IAccountService {
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

	public DtoAccount findAccountById(Long id);
	
	public List<DtoAccount> getAccounts();
	
	public DtoAccount updateAccount(DtoAccountIU dtoAccountIU , Long id);
	
	public void deleteAccount(Long id);
	
}
