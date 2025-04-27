package com.alirizakaygusuz.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;
import com.alirizakaygusuz.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    DtoAddress addressToDtoAddress(Address address);

    Address dtoAddressIUToAddress(DtoAddressIU dtoAddressIU);

    DtoAddressIU dtoAddressToDtoAddressIU(DtoAddress dtoAddress);
    
    @BeanMapping(ignoreByDefault = false)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    void updateAddressFromDtoAddressIU(DtoAddressIU dtoAddressIU, @MappingTarget Address address);

}

