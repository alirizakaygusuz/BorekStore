package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;

public interface IStoreBorekService {

	public DtoStoreBorek saveBorek(DtoStoreBorekIU dtoStoreBorekIU);

	public DtoStoreBorek findBorekById(Long id);

	public List<DtoStoreBorek> getAllBoreks();

	public DtoStoreBorek updateBorek(DtoStoreBorekIU dtoStoreBorekIU, Long id);
	
	public void deleteBorek(Long id);

}
