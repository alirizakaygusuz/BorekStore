package com.alirizakaygusuz.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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
import com.alirizakaygusuz.repository.CustomerRepository;
import com.alirizakaygusuz.repository.StoreRepository;
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
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private StoreRepository storeRepository;

	private boolean isValidBirthDate(LocalDate birthDate) {
		LocalDate today = LocalDate.now();
		int age = Period.between(birthDate, today).getYears();
		return age >= 12 && age <= 120;
	}
	
	private void isValidIdentityNumber(String identityNumber) {
		if (identityNumber == null || identityNumber.length() != 11 || !identityNumber.chars().allMatch(Character::isDigit)) {
	        throw new BaseException(new ErrorMessage(
	            ErrorType.INVALID_INPUT, 
	            "Identity number must be exactly 11 digits"
	        ));
	    }
	}
	
	private void isValidCardNumber(String cardNumber) {
		 if (cardNumber == null || cardNumber.length() != 16 || !cardNumber.chars().allMatch(Character::isDigit)) {
		        throw new BaseException(new ErrorMessage(
		            ErrorType.INVALID_INPUT,
		            "Card number must be exactly 16 digits"
		        ));
		    }

	}

	private Account createAccount(DtoAccountIU dtoAccountIU) {

		if (!isValidBirthDate(dtoAccountIU.getBirthDate())) {
			throw new BaseException(new ErrorMessage(ErrorType.INVALID_BIRTH_DATE));
		}
		
		isValidIdentityNumber(dtoAccountIU.getIdentityNumber());
		isValidCardNumber(dtoAccountIU.getCardNo());
		Account account = accountMapper.dtoAccountIUToAccount(dtoAccountIU);

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

		if(accountRepository.existsByAddressIdAndIdNot(dtoAccountIU.getAddress_id(), id)) {
			 throw new BaseException(new ErrorMessage(ErrorType.ADDRESS_IS_USED_BY_ACCOUNT));
		}
		Address address = addressService.findAddressByIdThrow(dtoAccountIU.getAddress_id());

		account.setAddress(address);

		Account updatedAccount = accountRepository.save(account);

		return accountMapper.accountToDtoAccount(updatedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = findAccountByIdThrow(id);
		
		if(customerRepository.existsByAccountsId(id) || storeRepository.existsByAccountId(id)) {
			 throw new BaseException(new ErrorMessage(ErrorType.ACCOUNT_IS_USED_BY_CUSTOMER_OR_STORE));

		}
		
		accountRepository.delete(account);

	}

	@Override
	public Account updateAccountAmount(Account account) {
	
		return accountRepository.save(account);
	}

	

}
