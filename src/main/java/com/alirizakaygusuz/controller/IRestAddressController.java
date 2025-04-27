package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;

public interface IRestAddressController {
	RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	
	RootEntity<DtoAddress> findAddressById(Long id);

	RootEntity<List<DtoAddress>> getAllAddresses();
	
	RootEntity<DtoAddress> updateAddress(DtoAddressIU dtoAddressIU,Long id);
	
    RootEntity<String> deleteAddress(Long id);

}
