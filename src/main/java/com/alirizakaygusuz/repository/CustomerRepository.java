package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alirizakaygusuz.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByAccountsId(Long accountId);

	boolean existsByAccountsIdAndIdNot(Long accountId, Long customerId);
	
}
