package com.alirizakaygusuz.service;

import java.util.List;

import com.alirizakaygusuz.dto.DtoCustomer;
import com.alirizakaygusuz.dto.DtoCustomerIU;
import com.alirizakaygusuz.entity.Customer;

public interface ICustomerService {
	
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	
	public DtoCustomer findCustomerById(Long id);
	
	public List<DtoCustomer> getAllCustomers();
	
	public DtoCustomer updateCustomer(DtoCustomerIU dtoCustomerIU , Long id);
	
	public void deleteCustomer(Long id);
	
	public Customer findCustomerByIdThrow(Long id);
	

}
