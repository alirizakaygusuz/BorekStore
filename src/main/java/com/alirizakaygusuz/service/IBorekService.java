package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;
import com.alirizakaygusuz.entity.Borek;

public interface IBorekService {
	
	public DtoBorek saveBorek(DtoBorekIU dtoBorekIU);
	
	public DtoBorek findBorekById(Long id);
	
	public List<DtoBorek> getAllBoreks();
	
	
	public DtoBorek updateBorek(DtoBorekIU dtoBorekIU,Long id);
	
	
	public void deleteBorek(Long id);
	
	public Borek findBorekByIdThrow(Long id);
	
	

	public Borek changeBorekStatus(Borek borek);

}
