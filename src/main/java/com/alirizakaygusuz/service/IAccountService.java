package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoAccountIU;
import com.alirizakaygusuz.entity.Account;

public interface IAccountService {
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

	public DtoAccount findAccountById(Long id);
	
	public List<DtoAccount> getAccounts();
	
	public DtoAccount updateAccount(DtoAccountIU dtoAccountIU , Long id);
	
	public void deleteAccount(Long id);
	
	public Account findAccountByIdThrow(Long id);
	
	
	public Account updateAccountAmount(Account account);

	
}
