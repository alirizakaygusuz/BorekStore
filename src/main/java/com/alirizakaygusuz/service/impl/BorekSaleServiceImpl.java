package com.alirizakaygusuz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.DtoBorekSaleIU;
import com.alirizakaygusuz.entity.Borek;
import com.alirizakaygusuz.entity.BorekSale;
import com.alirizakaygusuz.entity.Customer;
import com.alirizakaygusuz.entity.DtoBorekSale;
import com.alirizakaygusuz.entity.Store;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.BorekSaleMapper;
import com.alirizakaygusuz.repository.BorekSaleRepository;
import com.alirizakaygusuz.service.IBorekSaleService;
import com.alirizakaygusuz.service.IBorekService;
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

	
	private BorekSale createBorekSale(DtoBorekSaleIU dtoBorekSaleIU) {

		Store store = storeService.findStoreByIdThrow(dtoBorekSaleIU.getStoreId());
		Borek borek = borekService.findBorekByIdThrow(dtoBorekSaleIU.getBorekId());
		
		Customer customer = customerService.findCustomerByIdThrow(dtoBorekSaleIU.getCustomerId());

		BorekSale borekSale = new BorekSale();

		borekSale.setStore(store);
		borekSale.setBorek(borek);
		borekSale.setCustomer(customer);

		return borekSale;
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

	@Override
	public DtoBorekSale saveBorekSale(DtoBorekSaleIU dtoBorekSaleIU) {
		ensureBorekNotAssignedToAnySale(dtoBorekSaleIU.getBorekId());
		ensureAssignableForBorekSale(dtoBorekSaleIU);

		BorekSale borekSale = createBorekSale(dtoBorekSaleIU);
		borekSale.setCreateTime(new Date());

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

	@Override
	public DtoBorekSale updateBorekSale(DtoBorekSaleIU dtoBorekSaleIU, Long id) {

		BorekSale borekSale = findBorekSaleByIdThrow(id);

		ensureBorekNotAssignedToAnySale(dtoBorekSaleIU.getBorekId(), id);

		ensureAssignableForBorekSale(dtoBorekSaleIU, id);

		BorekSale borekSaleInput = createBorekSale(dtoBorekSaleIU);

		borekSale.setBorek(borekSaleInput.getBorek());
		borekSale.setStore(borekSaleInput.getStore());
		borekSale.setCustomer(borekSaleInput.getCustomer());

		BorekSale updatedBorekSale = borekSaleRepository.save(borekSale);

		return borekSaleMapper.borekSaleToDtoBorekSale(updatedBorekSale);
	}

	@Override
	public void deleteBorekSale(Long id) {
		BorekSale borekSale = findBorekSaleByIdThrow(id);

		borekSaleRepository.delete(borekSale);

	}

}
