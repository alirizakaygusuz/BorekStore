package com.alirizakaygusuz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;
import com.alirizakaygusuz.entity.Address;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.AddressMapper;
import com.alirizakaygusuz.repository.AddressRepository;
import com.alirizakaygusuz.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressMapper addressMapper;

	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address currentAddress = addressMapper.dtoAddressIUToAddress(dtoAddressIU);

		return currentAddress;
	}
	
	@Override
	public Address findAddressByIdThrow(Long id) {
		return addressRepository.findById(id).orElseThrow(() -> new BaseException(
				new ErrorMessage(ErrorType.ADDRESS_NOT_FOUND, "Address not found with id: " + id)));
	}

	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));

		return addressMapper.addressToDtoAddress(savedAddress);

	}
	
	

	@Override
	public DtoAddress findAddressById(Long id) {
		Address address = findAddressByIdThrow(id);
		
		
		return addressMapper.addressToDtoAddress(address);
	}

	@Override 
	public List<DtoAddress> getAllAddresses() {
		List<DtoAddress> dtoAdressList =  new ArrayList<>();
		List<Address> addresList = addressRepository.findAll();
		
	
		
		for(Address address:addresList) {
			dtoAdressList.add(addressMapper.addressToDtoAddress(address));
		}
		
		
		
		return dtoAdressList;
	}

	@Override
	public void deleteAddress(Long id) {
		 Address address = findAddressByIdThrow(id);

			    addressRepository.delete(address);
		
	}

	@Override
	public DtoAddress updateAddress(DtoAddressIU dtoAddressIU, Long id) {
		Address address = findAddressByIdThrow(id);

		addressMapper.updateAddressFromDtoAddressIU(dtoAddressIU, address);
		
		Address updatedAddress = addressRepository.save(address);
		
		
		return addressMapper.addressToDtoAddress(updatedAddress);
		
		
	}

}
