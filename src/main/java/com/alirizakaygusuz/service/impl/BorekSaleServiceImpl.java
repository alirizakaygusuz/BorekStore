package com.alirizakaygusuz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.dto.DtoBorekSale;
import com.alirizakaygusuz.dto.DtoBorekSaleIU;
import com.alirizakaygusuz.entity.Account;
import com.alirizakaygusuz.entity.Borek;
import com.alirizakaygusuz.entity.BorekSale;
import com.alirizakaygusuz.entity.Customer;
import com.alirizakaygusuz.entity.Store;
import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.BorekSaleMapper;
import com.alirizakaygusuz.repository.BorekSaleRepository;
import com.alirizakaygusuz.service.IAccountService;
import com.alirizakaygusuz.service.IBorekSaleService;
import com.alirizakaygusuz.service.IBorekService;
import com.alirizakaygusuz.service.ICurrencyConversionService;
import com.alirizakaygusuz.service.ICustomerService;
import com.alirizakaygusuz.service.IStoreService;

@Service
public class BorekSaleServiceImpl implements IBorekSaleService {

	@Autowired
	private BorekSaleRepository borekSaleRepository;

	@Autowired
	private BorekSaleMapper borekSaleMapper;

	@Autowired
	private IStoreService storeService;

	@Autowired
	private IBorekService borekService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICurrencyConversionService currencyConversionService;

	@Autowired
	private IAccountService accountService;

	private void ensureBorekSalable(Borek borek) {
		if (borek.getBorekStatus() == BorekStatus.SALABLE) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREK_NOT_IN_STORE));

		} else if (borek.getBorekStatus() == BorekStatus.OUT_OF_STOCK) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREK_OUT_OF_STOCK));

		}
	}

	private BorekSale findBorekSaleByIdThrow(Long id) {
		return borekSaleRepository.findById(id).orElseThrow(
				() -> new BaseException(new ErrorMessage(ErrorType.BOREKSALE_NOT_FOUND, "Borek Sale Id:" + id)));
	}

	private void ensureAssignableForBorekSale(DtoBorekSaleIU dtoBorekSaleIU) {
		boolean borekSaleIn = borekSaleRepository.existsByStoreIdAndBorekIdAndCustomerId(dtoBorekSaleIU.getStoreId(),
				dtoBorekSaleIU.getBorekId(), dtoBorekSaleIU.getCustomerId());

		if (borekSaleIn) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREKSALE__ALREADY_ASSIGNED));
		}
	}

	private void ensureAssignableForBorekSale(DtoBorekSaleIU dtoBorekSaleIU, Long excludedId) {
		boolean borekSaleIn = borekSaleRepository.existsByStoreIdAndBorekIdAndCustomerIdAndIdNot(
				dtoBorekSaleIU.getStoreId(), dtoBorekSaleIU.getBorekId(), dtoBorekSaleIU.getCustomerId(), excludedId);

		if (borekSaleIn) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREKSALE__ALREADY_ASSIGNED));
		}
	}

	private void ensureBorekNotAssignedToAnySale(Long borekId) {
		boolean borekAssigned = borekSaleRepository.existsByBorekId(borekId);

		if (borekAssigned) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREKSALE_BOREK_ALREADY_EXISTS, "borek id:" + borekId));
		}
	}

	private void ensureBorekNotAssignedToAnySale(Long borekId, Long excludedId) {
		boolean borekAssigned = borekSaleRepository.existsByBorekIdAndIdNot(borekId, excludedId);

		if (borekAssigned) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREKSALE_BOREK_ALREADY_EXISTS, "borek id:" + borekId));
		}
	}

	private Account chargeCustomer(Customer customer, Store store, Borek borek) {
		BigDecimal borekPriceInCurrentCurrency = null;
		BigDecimal incomeAmountFromStore = null;
		Account activeAccount = new Account();
		boolean isAffordable = false;

		for (Account account : customer.getAccounts()) {
			BigDecimal current = account.getAmount();

			borekPriceInCurrentCurrency = currencyConversionService
					.convertToAud(account.getCurrencyType().name(), borek.getPrice().doubleValue());
			incomeAmountFromStore= currencyConversionService.convertToAud(store.getAccount().getCurrencyType().name(),
						borek.getPrice().doubleValue());
			

			if (current.subtract(borekPriceInCurrentCurrency).compareTo(BigDecimal.ZERO) > 0) {
				activeAccount = account;
				isAffordable = true;
				break;
			}
		}

		if (!isAffordable || borekPriceInCurrentCurrency == null || incomeAmountFromStore==null) {
			throw new BaseException(new ErrorMessage(ErrorType.INSUFFICIENT_FUNDS));

		}

		activeAccount.setAmount(activeAccount.getAmount().subtract(borekPriceInCurrentCurrency));
		store.getAccount().setAmount(store.getAccount().getAmount().add(incomeAmountFromStore));

		accountService.updateAccountAmount(activeAccount);
		accountService.updateAccountAmount(store.getAccount());

		return activeAccount;

	}

	private BorekSale createBorekSale(DtoBorekSaleIU dtoBorekSaleIU) {

		Store store = storeService.findStoreByIdThrow(dtoBorekSaleIU.getStoreId());
		Borek borek = borekService.findBorekByIdThrow(dtoBorekSaleIU.getBorekId());
		ensureBorekSalable(borek);

		Customer customer = customerService.findCustomerByIdThrow(dtoBorekSaleIU.getCustomerId());

		Account activeAccount = chargeCustomer(customer, store, borek);

		borek.setBorekStatus(BorekStatus.OUT_OF_STOCK);
		borekService.changeBorekStatus(borek);

		BorekSale borekSale = new BorekSale();

		borekSale.setStore(store);
		borekSale.setBorek(borek);
		borekSale.setCustomer(customer);
		borekSale.setAccount(activeAccount);

		return borekSale;
	}

	@Transactional
	@Override
	public DtoBorekSale saveBorekSale(DtoBorekSaleIU dtoBorekSaleIU) {
		ensureBorekNotAssignedToAnySale(dtoBorekSaleIU.getBorekId());
		ensureAssignableForBorekSale(dtoBorekSaleIU);

		BorekSale borekSale = createBorekSale(dtoBorekSaleIU);

		BorekSale savedBorekSale = borekSaleRepository.save(borekSale);

		return borekSaleMapper.borekSaleToDtoBorekSale(savedBorekSale);
	}

	@Override
	public DtoBorekSale getBorekSaleById(Long id) {
		BorekSale borekSale = findBorekSaleByIdThrow(id);
		return borekSaleMapper.borekSaleToDtoBorekSale(borekSale);
	}

	@Override
	public List<DtoBorekSale> getBorekSales() {
		List<BorekSale> borekSaleList = borekSaleRepository.findAll();
		List<DtoBorekSale> dtoBorekSaleList = new ArrayList<>();

		for (BorekSale borekSale : borekSaleList) {
			dtoBorekSaleList.add(borekSaleMapper.borekSaleToDtoBorekSale(borekSale));
		}
		return dtoBorekSaleList;
	}

	private void refundPreviousTransaction(BorekSale borekSale) {
		Borek borek = borekSale.getBorek();
		Store store = borekSale.getStore();
		Account account = borekSale.getAccount();
		
		BigDecimal refundedAmountToCustomer = currencyConversionService.convertToAud(account.getCurrencyType().name(),
				borek.getPrice().doubleValue());
		
		account.setAmount(account.getAmount().add(refundedAmountToCustomer));
		
		BigDecimal deductedAmountFromStore = currencyConversionService.convertToAud(store.getAccount().getCurrencyType().name(),
				borek.getPrice().doubleValue());
		
		store.getAccount().setAmount(store.getAccount().getAmount().subtract(deductedAmountFromStore));
		
		borek.setBorekStatus(BorekStatus.SOLD);
		
		borekService.changeBorekStatus(borek);
		accountService.updateAccountAmount(account);
		accountService.updateAccountAmount(store.getAccount());
		
	}

	@Transactional
	@Override
	public DtoBorekSale updateBorekSale(DtoBorekSaleIU dtoBorekSaleIU, Long id) {

		BorekSale borekSale = findBorekSaleByIdThrow(id);
		
		ensureBorekNotAssignedToAnySale(dtoBorekSaleIU.getBorekId(), id);

		ensureAssignableForBorekSale(dtoBorekSaleIU, id);

		refundPreviousTransaction(borekSale);

		BorekSale borekSaleInput = createBorekSale(dtoBorekSaleIU);
		


		borekSale.setBorek(borekSaleInput.getBorek());
		borekSale.setStore(borekSaleInput.getStore());
		borekSale.setCustomer(borekSaleInput.getCustomer());
		borekSale.setAccount(borekSaleInput.getAccount());

		BorekSale updatedBorekSale = borekSaleRepository.save(borekSale);

		return borekSaleMapper.borekSaleToDtoBorekSale(updatedBorekSale);
	}

	@Transactional
	@Override
	public void deleteBorekSale(Long id) {
		BorekSale borekSale = findBorekSaleByIdThrow(id);
		

		borekSaleRepository.delete(borekSale);

	}

}
