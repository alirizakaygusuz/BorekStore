package com.alirizakaygusuz.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoAccountIU;
import com.alirizakaygusuz.entity.Account;

@Mapper(componentModel = "spring" , uses = {AddressMapper.class})
public interface AccountMapper {

	DtoAccount accountToDtoAccount(Account account);

	@Mapping(target = "address",source = "address_id")
	Account dtoAccountIUToAccount(DtoAccountIU dtoAccountIU);
	
	@Mapping(target = "address_id",source = "address")
	DtoAccountIU dtoAccountToAccountIU(DtoAccount dtoAccount);

	@BeanMapping(ignoreByDefault = false)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createTime", ignore = true)
    @Mapping(target = "address", expression = "java(dtoAccountIU.getAddress_id() != null ? addressMapper.fromId(dtoAccountIU.getAddress_id()) : null)")
	void updateAccountFromDtoAccountIU(DtoAccountIU dtoAccountIU, @MappingTarget Account account,@Context AddressMapper addressMapper);
	
	


}
