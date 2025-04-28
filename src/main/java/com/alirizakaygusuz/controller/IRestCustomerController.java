package com.alirizakaygusuz.controller;

import java.util.List;

import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoCustomer;
import com.alirizakaygusuz.dto.DtoCustomerIU;

public interface IRestCustomerController {

	RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

	RootEntity<DtoCustomer> findCustomerById(Long id);

	RootEntity<List<DtoCustomer>> getAllCustomers();
	
	RootEntity<DtoCustomer> updateCustomer(DtoCustomerIU dtoCustomerIU,Long id);
	
	RootEntity<String> deleteCustomer(Long id);

}
