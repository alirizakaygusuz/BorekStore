package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;

public interface IAddressService {
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public DtoAddress findAddressById(Long id);
	
	public List<DtoAddress> getAllAddresses();
	
	
	public void deleteAddress(Long id);
	
	public DtoAddress updateAddress(DtoAddressIU dtoAddressIU, Long id);
	

}
