package com.alirizakaygusuz.mapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.alirizakaygusuz.dto.DtoAccount;
import com.alirizakaygusuz.dto.DtoCustomer;
import com.alirizakaygusuz.dto.DtoCustomerIU;
import com.alirizakaygusuz.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	DtoCustomer customerToDtoCustomer(Customer customer);

	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "customerName", source = "customerName")
	Customer dtoCustomerIUToCustomer(DtoCustomerIU dtoCustomerIU);

	@Mapping(target = "accountIds", source = "accounts", qualifiedByName = "mapAccountsToAccountIds")
	DtoCustomerIU dtoCustomerToDtoCustomerIU(DtoCustomer dtoCustomer);

	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "customerName", source = "customerName")
	void updateCustomerFromDtoCustomerIU(DtoCustomerIU dtoCustomerIU,@MappingTarget Customer customer);

	

	@Named("mapAccountsToAccountIds")
	default Set<Long> mapAccountsToAccountIds(Set<DtoAccount> accounts) {
	    if (accounts == null ) {
	    	return Collections.emptySet();
	    }

	    Set<Long> accountIds = new HashSet<>();
	    for (DtoAccount account : accounts) {
	        accountIds.add(account.getId());
	    }
	    return accountIds;
	}

}
