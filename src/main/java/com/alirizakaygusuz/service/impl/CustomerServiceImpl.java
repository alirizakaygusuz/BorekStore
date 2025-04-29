package com.alirizakaygusuz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.dto.DtoCustomer;
import com.alirizakaygusuz.dto.DtoCustomerIU;
import com.alirizakaygusuz.entity.Account;
import com.alirizakaygusuz.entity.Customer;
import com.alirizakaygusuz.exception.BaseException;
import com.alirizakaygusuz.exception.ErrorMessage;
import com.alirizakaygusuz.exception.ErrorType;
import com.alirizakaygusuz.mapper.CustomerMapper;
import com.alirizakaygusuz.repository.CustomerRepository;
import com.alirizakaygusuz.service.IAccountService;
import com.alirizakaygusuz.service.ICustomerService;
import com.alirizakaygusuz.validator.AccountAssignmentValidator;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountAssignmentValidator accountAssginmentValidator;
	

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	private IAccountService accountService;

	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {

		Customer customer = customerMapper.dtoCustomerIUToCustomer(dtoCustomerIU);
		customer.setCreateTime(new Date());

		Set<Account> accounts = fetchAccountsByIds(dtoCustomerIU.getAccountIds());

		customer.setAccounts(accounts);

		return customer;
	}

	private Set<Account> fetchAccountsByIds(Set<Long> accountIds) {

		Set<Account> accounts = new HashSet<>();

		for (Long accountId : accountIds) {
			Account accountById = accountService.findAccountByIdThrow(accountId);

			accounts.add(accountById);
		}

		return accounts;
	}

	

	@Transactional
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		accountAssginmentValidator.ensureAssignableForCustomer(dtoCustomerIU.getAccountIds());

		Customer customer = createCustomer(dtoCustomerIU);

		Customer savedCustomer = customerRepository.save(customer);

		return customerMapper.customerToDtoCustomer(savedCustomer);
	}

	public Customer findCustomerByIdThrow(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(ErrorType.CUSTOMER_NOT_FOUND, "id:" + id)));
	}

	@Override
	public DtoCustomer findCustomerById(Long id) {
		Customer customer = findCustomerByIdThrow(id);

		return customerMapper.customerToDtoCustomer(customer);
	}

	@Override
	public List<DtoCustomer> getAllCustomers() {
		List<DtoCustomer> dtoCustomerList = new ArrayList<>();

		List<Customer> customerList = customerRepository.findAll();

		for (Customer customer : customerList) {
			dtoCustomerList.add(customerMapper.customerToDtoCustomer(customer));
		}

		return dtoCustomerList;
	}

	@Transactional
	@Override
	public DtoCustomer updateCustomer(DtoCustomerIU dtoCustomerIU, Long id) {
		Customer customer = findCustomerByIdThrow(id);

		accountAssginmentValidator.ensureAssignableForCustomer(dtoCustomerIU.getAccountIds(),id);

		customerMapper.updateCustomerFromDtoCustomerIU(dtoCustomerIU, customer);

		Set<Account> accounts = fetchAccountsByIds(dtoCustomerIU.getAccountIds());

		customer.setAccounts(accounts);

		Customer updatedCustomer = customerRepository.save(customer);

		return customerMapper.customerToDtoCustomer(updatedCustomer);
	}

	@Transactional
	@Override
	public void deleteCustomer(Long id) {

		Customer customer = findCustomerByIdThrow(id);
		customerRepository.delete(customer);

	}

}
