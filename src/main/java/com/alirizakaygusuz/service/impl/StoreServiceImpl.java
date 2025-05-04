package com.alirizakaygusuz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.DtoStore;
import com.alirizakaygusuz.dto.DtoStoreIU;
import com.alirizakaygusuz.entity.Account;
import com.alirizakaygusuz.entity.Address;
import com.alirizakaygusuz.entity.Store;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.StoreMapper;
import com.alirizakaygusuz.repository.StoreBorekRepository;
import com.alirizakaygusuz.repository.StoreRepository;
import com.alirizakaygusuz.service.IAccountService;
import com.alirizakaygusuz.service.IAddressService;
import com.alirizakaygusuz.service.IStoreService;
import com.alirizakaygusuz.validator.AccountAssignmentValidator;

import jakarta.transaction.Transactional;

@Service
public class StoreServiceImpl implements IStoreService {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private AccountAssignmentValidator accountAssginmentValidator;
	

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private IAddressService addressService;

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private StoreBorekRepository storeBorekRepository;

	private void assignRelatedEntities(DtoStoreIU dtoStoreIU, Store store) {
		Address address = addressService.findAddressByIdThrow(dtoStoreIU.getAddressId());

		Account account = accountService.findAccountByIdThrow(dtoStoreIU.getAccountId());

		store.setAddress(address);
		store.setAccount(account);

	}
	


	private Store createStore(DtoStoreIU dtoStoreIU) {
		Store store = storeMapper.dtoStoreIUToStore(dtoStoreIU);

		assignRelatedEntities(dtoStoreIU, store);

		return store;

	}

	@Override
	public Store findStoreByIdThrow(Long id) {
		return storeRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(ErrorType.STORE_NOT_FOUND, "id: " + id)));
	}

	@Transactional
	@Override
	public DtoStore saveStore(DtoStoreIU dtoStoreIU) {
		accountAssginmentValidator.ensureAssignableForStore(dtoStoreIU.getAccountId());

		Store savedStore = storeRepository.save(createStore(dtoStoreIU));

		return storeMapper.storeToDtoStore(savedStore);
	}

	@Override
	public DtoStore findStoreById(Long id) {

		return storeMapper.storeToDtoStore(findStoreByIdThrow(id));
	}

	@Override
	public List<DtoStore> getAllStores() {
		List<Store> storeList = storeRepository.findAll();

		List<DtoStore> dtoStoreList = new ArrayList<>();

		for (Store store : storeList) {
			dtoStoreList.add(storeMapper.storeToDtoStore(store));
		}

		return dtoStoreList;
	}

	@Transactional
	@Override
	public DtoStore updateStore(DtoStoreIU dtoStoreIU, Long id) {
		Store store = findStoreByIdThrow(id);


		accountAssginmentValidator.ensureAssignableForStore(dtoStoreIU.getAccountId(),id);
		
		storeMapper.update(dtoStoreIU, store);
		assignRelatedEntities(dtoStoreIU, store);

		Store updatedStore = storeRepository.save(store);

		return storeMapper.storeToDtoStore(updatedStore);
	}

	@Transactional
	@Override
	public void deleteStore(Long id) {
		Store store = findStoreByIdThrow(id);
		if(storeBorekRepository.existsByStoreId(id)) {
			throw new BaseException(new ErrorMessage(ErrorType.STORE_CANNOT_BE_DELETED));
		}
		
		storeRepository.delete(store);

	}
}
