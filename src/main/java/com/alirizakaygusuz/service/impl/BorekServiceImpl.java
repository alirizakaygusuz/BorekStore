package com.alirizakaygusuz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;
import com.alirizakaygusuz.entity.Borek;
import com.alirizakaygusuz.enums.BorekStatus;
import com.alirizakaygusuz.enums.CurrencyType;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.BorekMapper;
import com.alirizakaygusuz.repository.BorekRepository;
import com.alirizakaygusuz.service.IBorekService;

@Service
public class BorekServiceImpl implements IBorekService{
	
	@Autowired
	private BorekRepository borekRepository;
	
	
	@Autowired
	private BorekMapper borekMapper;
	
	

	
	private Borek createBorek(DtoBorekIU dtoBorekIU) {
		Borek borek = borekMapper.dtoBorekIUToBorek(dtoBorekIU);
		borek.setBorekStatus(BorekStatus.SALABLE);
		borek.setCurrencyType(CurrencyType.AUD);
		
		
		return borek;
		
	}
	
	@Override
	public Borek findBorekByIdThrow(Long id) {
		return borekRepository.findById(id)
				.orElseThrow(() -> new BaseException(
						new ErrorMessage(ErrorType.BOREK_NOT_FOUND, "Borek not found with id: " + id)));
			}

	@Override
	public DtoBorek saveBorek(DtoBorekIU dtoBorekIU) {
		Borek savedBorek = borekRepository.save(createBorek(dtoBorekIU));
		
		return borekMapper.borekToDtoBorek(savedBorek);
	}

	@Override
	public DtoBorek findBorekById(Long id) {
		Borek borek = findBorekByIdThrow(id);
		return borekMapper.borekToDtoBorek(borek);
	}

	@Override
	public List<DtoBorek> getAllBoreks() {
		List<DtoBorek> dtoBorekList= new ArrayList<>();
		
		List<Borek> borekList= borekRepository.findAll();
		
		for(Borek borek: borekList) {
			dtoBorekList.add(borekMapper.borekToDtoBorek(borek));
		}
		
		
		
		return dtoBorekList;
	}

	@Override
	public DtoBorek updateBorek(DtoBorekIU dtoBorekIU, Long id) {
		Borek borek = findBorekByIdThrow(id);
		borekMapper.updateBorekFromDtoBorekIU(dtoBorekIU, borek);
		
		Borek updatedBorek = borekRepository.save(borek);
		
		return borekMapper.borekToDtoBorek(updatedBorek);
	}

	@Override
	public void deleteBorek(Long id) {
		Borek borek = findBorekByIdThrow(id);
		
		if(borek.getBorekStatus() != BorekStatus.SALABLE) {
			throw new BaseException(new ErrorMessage(ErrorType.BOREK_CANNOT_BE_DELETED));
		}
		
		borekRepository.delete(borek);
		
	}

	@Override
	public Borek changeBorekStatus(Borek borek) {

		return borekRepository.save(borek);
	}

}
