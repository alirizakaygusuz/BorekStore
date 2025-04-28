package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;
import com.alirizakaygusuz.entity.Address;

public interface IAddressService {
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	
	public DtoAddress findAddressById(Long id);
	
	public List<DtoAddress> getAllAddresses();
	
	
	public DtoAddress updateAddress(DtoAddressIU dtoAddressIU, Long id);
	
	public void deleteAddress(Long id);

	public Address findAddressByIdThrow(Long address_id);

}
