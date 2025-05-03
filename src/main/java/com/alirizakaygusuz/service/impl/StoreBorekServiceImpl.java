package com.alirizakaygusuz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;
import com.alirizakaygusuz.entity.Borek;
import com.alirizakaygusuz.entity.Store;
import com.alirizakaygusuz.entity.StoreBorek;
import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.StoreBorekMapper;
import com.alirizakaygusuz.repository.StoreBorekRepository;
import com.alirizakaygusuz.service.IAccountService;
import com.alirizakaygusuz.service.IBorekService;
import com.alirizakaygusuz.service.ICurrencyConversionService;
import com.alirizakaygusuz.service.IStoreBorekService;
import com.alirizakaygusuz.service.IStoreService;

@Service
public class StoreBorekServiceImpl implements IStoreBorekService {

	@Autowired
	private StoreBorekRepository storeBorekRepository;

	@Autowired
	private StoreBorekMapper storeBorekMapper;

	@Autowired
	private IStoreService storeService;

	@Autowired
	private IBorekService borekService;

	@Autowired
	private ICurrencyConversionService currencyConversionService;
	
	
	@Autowired
	private IAccountService accountService;

	private StoreBorek findStoreBorekByIdThrow(Long id) {
		return storeBorekRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(ErrorType.STOREBOREK_NOT_FOUND, "id:" + id)));
	}

	private void ensureBorekSalable(Borek borek) {
		if (borek.getBorekStatus() == BorekStatus.SOLD) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREK_ALREADY_SOLD));
		}
	}

	
	private void ensureAssignableForStoreBorek(DtoStoreBorekIU dtoStoreBorekIU) {
		boolean storeBorekIn = storeBorekRepository.existsByStoreIdAndBorekId(dtoStoreBorekIU.getStoreId(),
				dtoStoreBorekIU.getBorekId());

		if (storeBorekIn) {
			throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_ALREADY_EXISTS,
					"storeId:" + dtoStoreBorekIU.getStoreId() + ", borekId:" + dtoStoreBorekIU.getBorekId()));
		}
	}

	private void ensureAssignableForStoreBorek(DtoStoreBorekIU dtoStoreBorekIU, Long excludedId) {
		boolean storeBorekIn = storeBorekRepository.existsByStoreIdAndBorekIdAndIdNot(dtoStoreBorekIU.getStoreId(),
				dtoStoreBorekIU.getBorekId(), excludedId);

		if (storeBorekIn) {
			throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_ALREADY_EXISTS,
					"storeId:" + dtoStoreBorekIU.getStoreId() + ", borekId:" + dtoStoreBorekIU.getBorekId()));
		}
	}

	private void ensureBorekNotAssignedToAnyStore(Long borekId) {
		boolean borekAssigned = storeBorekRepository.existsByBorekId(borekId);

		if (borekAssigned) {
			throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_BOREK_ALREADY_EXISTS, "borek id:" + borekId));
		}
	}

	private void ensureBorekNotAssignedToAnyStore(Long borekId, Long excludedId) {
		boolean borekAssigned = storeBorekRepository.existsByBorekIdAndIdNot(borekId, excludedId);

		if (borekAssigned) {
			throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_BOREK_ALREADY_EXISTS, "borek id:" + borekId));
		}
	}
	
	
	private void chargeStoreAccount(Store store, BigDecimal deductionAmount) {
		BigDecimal current = store.getAccount().getAmount();
		if (current.subtract(deductionAmount).compareTo(BigDecimal.ZERO) < 0) {
			throw new BaseException(new ErrorMessage(ErrorType.INSUFFICIENT_FUNDS));
		}
		
		store.getAccount().setAmount(current.subtract(deductionAmount));
		
		accountService.updateAccountAmount(store.getAccount());


	}

	private StoreBorek createStoreBorek(DtoStoreBorekIU dtoStoreBorekIU) {

		StoreBorek storeBorek = new StoreBorek();

		Borek selectedBorek = borekService.findBorekByIdThrow(dtoStoreBorekIU.getBorekId());
		ensureBorekSalable(selectedBorek);

		storeBorek.setBorek(selectedBorek);
		storeBorek.setStore(storeService.findStoreByIdThrow(dtoStoreBorekIU.getStoreId()));

		BigDecimal borekPriceInCurrentCurrency = currencyConversionService.convertToAud(
				storeBorek.getStore().getAccount().getCurrencyType().name(), storeBorek.getBorek().getPrice().doubleValue());

		chargeStoreAccount(storeBorek.getStore(), borekPriceInCurrentCurrency);

		selectedBorek.setBorekStatus(BorekStatus.SOLD);
		borekService.changeBorekStatus(selectedBorek);

		return storeBorek;

	}


	@Transactional
	@Override
	public DtoStoreBorek saveStoreBorek(DtoStoreBorekIU dtoStoreBorekIU) {

		ensureBorekNotAssignedToAnyStore(dtoStoreBorekIU.getBorekId());

		ensureAssignableForStoreBorek(dtoStoreBorekIU);

		StoreBorek storeBorek = createStoreBorek(dtoStoreBorekIU);

		StoreBorek savedStoreBorek = storeBorekRepository.save(storeBorek);

		return storeBorekMapper.storeBorekToDtoStoreBorek(savedStoreBorek);
	}

	@Override
	public DtoStoreBorek findStoreBorekById(Long id) {
		StoreBorek storeBorek = findStoreBorekByIdThrow(id);
		return storeBorekMapper.storeBorekToDtoStoreBorek(storeBorek);
	}

	@Override
	public List<DtoStoreBorek> getAllStoreBoreks() {
		List<StoreBorek> storeBorekList = storeBorekRepository.findAll();
		List<DtoStoreBorek> dtoStoreBorekList = new ArrayList<>();

		for (StoreBorek storeBorek : storeBorekList) {
			dtoStoreBorekList.add(storeBorekMapper.storeBorekToDtoStoreBorek(storeBorek));
		}

		return dtoStoreBorekList;
	}

	private void refundPreviousTransaction(StoreBorek storeBorek) {
		Borek borek = storeBorek.getBorek();

		Store store = storeBorek.getStore();

		BigDecimal oldPrice = currencyConversionService.convertToAud(store.getAccount().getCurrencyType().name(),
				borek.getPrice().doubleValue());

		borek.setBorekStatus(BorekStatus.SALABLE);
		store.getAccount().setAmount(store.getAccount().getAmount().add(oldPrice));
		
		borekService.changeBorekStatus(borek);
		accountService.updateAccountAmount(store.getAccount());
		

	}

	@Transactional
	@Override
	public DtoStoreBorek updateStoreBorek(DtoStoreBorekIU dtoStoreBorekIU, Long id) {

		StoreBorek storeBorek = findStoreBorekByIdThrow(id);
		

		ensureBorekNotAssignedToAnyStore(dtoStoreBorekIU.getBorekId(), id);

		ensureAssignableForStoreBorek(dtoStoreBorekIU, id);
		
		refundPreviousTransaction(storeBorek);


		StoreBorek storeBorekInput = createStoreBorek(dtoStoreBorekIU);

		storeBorek.setBorek(storeBorekInput.getBorek());
		storeBorek.setStore(storeBorekInput.getStore());

		StoreBorek updatedStoreBorek = storeBorekRepository.save(storeBorek);

		return storeBorekMapper.storeBorekToDtoStoreBorek(updatedStoreBorek);
	}

	@Transactional
	@Override
	public void deleteStoreBorek(Long id) {
		StoreBorek storeBorek = findStoreBorekByIdThrow(id);

		storeBorekRepository.delete(storeBorek);

	}

}
