package com.alirizakaygusuz.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;
import com.alirizakaygusuz.entity.Address;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    DtoAddress addressToDtoAddress(Address address);

    
    Address dtoAddressIUToAddress(DtoAddressIU dtoAddressIU);

    DtoAddressIU dtoAddressToDtoAddressIU(DtoAddress dtoAddress);
    
    @BeanMapping(ignoreByDefault = false)
    void updateAddressFromDtoAddressIU(DtoAddressIU dtoAddressIU, @MappingTarget Address address);
    
    
    default Address fromId(Long id) {
    	if(id == null) {
	        throw new BaseException(new ErrorMessage(ErrorType.ADDRESS_NOT_FOUND, "Address ID is missing"));

    	}
    	 Address address = new Address();
         address.setId(id);
         return address;
    }

    
    default Long toId(Address address) {
    	if(address == null) {
	        throw new BaseException(new ErrorMessage(ErrorType.ADDRESS_NOT_FOUND, "Address is not found"));

    	}
    	
    	return address.getId();
    }
}

