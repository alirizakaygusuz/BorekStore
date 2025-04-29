package com.alirizakaygusuz.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoAccountIU;
import com.alirizakaygusuz.entity.Account;
import com.alirizakaygusuz.entity.Address;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.AccountMapper;
import com.alirizakaygusuz.mapper.AddressMapper;
import com.alirizakaygusuz.repository.AccountRepository;
import com.alirizakaygusuz.service.IAccountService;
import com.alirizakaygusuz.service.IAddressService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private AddressMapper addressMapper;

	private boolean isValidBirthDate(LocalDate birthDate) {
		LocalDate today = LocalDate.now();
		int age = Period.between(birthDate, today).getYears();
		return age >= 12 && age <= 120;
	}

	private Account createAccount(DtoAccountIU dtoAccountIU) {

		if (!isValidBirthDate(dtoAccountIU.getBirthDate())) {
			throw new BaseException(new ErrorMessage(ErrorType.INVALID_BIRTH_DATE));
		}
		Account account = accountMapper.dtoAccountIUToAccount(dtoAccountIU);
		account.setCreateTime(new Date());

		return account;

	}

	private void ensureCardNoAndIdentityNumberNotAssignedToAnyAccount(String identityNumber, String cardNo) {
		if (accountRepository.existsByIdentityNumber(identityNumber)) {
			throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_IDENTITYNUMBER_ALREADY_EXISTS,
					"Identity Number: " + identityNumber));
		}

		if (accountRepository.existsByCardNo(cardNo)) {
			throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_CARDNO_ALREADY_EXISTS, "Card No: " + cardNo));
		}
	}

	private void ensureCardNoAndIdentityNumberNotAssignedToAnyAccount(String identityNumber, String cardNo,
			Long accountId) {
		if (accountRepository.existsByIdentityNumberAndIdNot(identityNumber, accountId)) {
			throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_IDENTITYNUMBER_ALREADY_EXISTS,
					"Identity Number: " + identityNumber));
		}

		if (accountRepository.existsByCardNoAndIdNot(cardNo, accountId)) {
			throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_CARDNO_ALREADY_EXISTS, "Card No: " + cardNo));
		}
	}

	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		ensureCardNoAndIdentityNumberNotAssignedToAnyAccount(dtoAccountIU.getIdentityNumber(),
				dtoAccountIU.getCardNo());

		Address address = addressService.findAddressByIdThrow(dtoAccountIU.getAddress_id());

		Account account = createAccount(dtoAccountIU);

		account.setAddress(address);

		Account savedAccount = accountRepository.save(account);

		return accountMapper.accountToDtoAccount(savedAccount);
	}

	@Override
	public Account findAccountByIdThrow(Long id) {
		return accountRepository.findById(id).orElseThrow(() -> new BaseException(
				new ErrorMessage(ErrorType.ACCOUNT_NOT_FOUND, "Account not found with id:" + id)));

	}

	@Override
	public DtoAccount findAccountById(Long id) {
		Account account = findAccountByIdThrow(id);

		return accountMapper.accountToDtoAccount(account);
	}

	@Override
	public List<DtoAccount> getAccounts() {
		List<DtoAccount> dtoAccounts = new ArrayList<>();

		List<Account> accounts = accountRepository.findAll();

		for (Account account : accounts) {
			dtoAccounts.add(accountMapper.accountToDtoAccount(account));
		}

		return dtoAccounts;
	}

	@Override
	public DtoAccount updateAccount(DtoAccountIU dtoAccountIU, Long id) {

		ensureCardNoAndIdentityNumberNotAssignedToAnyAccount(dtoAccountIU.getIdentityNumber(), dtoAccountIU.getCardNo(),id);
		Account account = findAccountByIdThrow(id);

		accountMapper.updateAccountFromDtoAccountIU(dtoAccountIU, account, addressMapper);

		Address address = addressService.findAddressByIdThrow(dtoAccountIU.getAddress_id());

		account.setAddress(address);

		Account updatedAccount = accountRepository.save(account);

		return accountMapper.accountToDtoAccount(updatedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = findAccountByIdThrow(id);
		accountRepository.delete(account);

	}

}
