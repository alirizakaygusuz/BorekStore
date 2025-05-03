package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;
import com.alirizakaygusuz.entity.Account;

public interface IStoreBorekService {

	public DtoStoreBorek saveStoreBorek(DtoStoreBorekIU dtoStoreBorekIU);

	public DtoStoreBorek findStoreBorekById(Long id);

	public List<DtoStoreBorek> getAllStoreBoreks();

	public DtoStoreBorek updateStoreBorek(DtoStoreBorekIU dtoStoreBorekIU, Long id);
	
	public void deleteStoreBorek(Long id);
	

}
