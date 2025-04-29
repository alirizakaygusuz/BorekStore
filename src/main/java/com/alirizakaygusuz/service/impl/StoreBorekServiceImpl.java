package com.alirizakaygusuz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;
import com.alirizakaygusuz.entity.StoreBorek;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.StoreBorekMapper;
import com.alirizakaygusuz.repository.StoreBorekRepository;
import com.alirizakaygusuz.service.IBorekService;
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

	private StoreBorek findStoreBorekByIdThrow(Long id) {
		return storeBorekRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(ErrorType.STOREBOREK_NOT_FOUND, "id:" + id)));
	}

	private StoreBorek createStoreBorek(DtoStoreBorekIU dtoStoreBorekIU) {

		StoreBorek storeBorek = new StoreBorek();

		storeBorek.setBorek(borekService.findBorekByIdThrow(dtoStoreBorekIU.getBorekId()));
		storeBorek.setStore(storeService.findStoreByIdThrow(dtoStoreBorekIU.getStoreId()));

		return storeBorek;
	}

	private void ensureAssignableForStoreBorek(DtoStoreBorekIU dtoStoreBorekIU) {
		boolean storeBorekIn = storeBorekRepository.existsByStoreIdAndBorekId(dtoStoreBorekIU.getStoreId(),
				dtoStoreBorekIU.getBorekId());

		if (storeBorekIn) {
			throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_ALREADY_EXISTS,
					"storeId:" + dtoStoreBorekIU.getStoreId() + ", borekId:" + dtoStoreBorekIU.getBorekId()));
		}
	}

	private void ensureAssignableForStoreBorek(DtoStoreBorekIU dtoStoreBorekIU, Long id) {
		boolean storeBorekIn = storeBorekRepository.existsByStoreIdAndBorekIdAndIdNot(dtoStoreBorekIU.getStoreId(),
				dtoStoreBorekIU.getBorekId(),id);

		if (storeBorekIn) {
			throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_ALREADY_EXISTS,
					"storeId:" + dtoStoreBorekIU.getStoreId() + ", borekId:" + dtoStoreBorekIU.getBorekId()));
		}
	}

	private void ensureBorekNotAssignedToAnyStore(Long borekId) {
	    boolean borekAssigned = storeBorekRepository.existsByBorekId(borekId);

	    if (borekAssigned) {
	        throw new BaseException(new ErrorMessage(ErrorType.STOREBOREK_BOREK_ALREADY_EXISTS,
	            "borek id:"+borekId));
	    }
	}
	
	
	@Transactional
	@Override
	public DtoStoreBorek saveStoreBorek(DtoStoreBorekIU dtoStoreBorekIU) {
		
	    ensureBorekNotAssignedToAnyStore(dtoStoreBorekIU.getBorekId());
	    
	    
		ensureAssignableForStoreBorek(dtoStoreBorekIU);
		
		
		StoreBorek savedStoreBorek = storeBorekRepository.save(createStoreBorek(dtoStoreBorekIU));
		savedStoreBorek.setCreateTime(new Date());

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

	@Transactional
	@Override
	public DtoStoreBorek updateStoreBorek(DtoStoreBorekIU dtoStoreBorekIU, Long id) {
	    
		ensureBorekNotAssignedToAnyStore(dtoStoreBorekIU.getBorekId());

		ensureAssignableForStoreBorek(dtoStoreBorekIU, id);

		
		StoreBorek storeBorek = findStoreBorekByIdThrow(id);

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
