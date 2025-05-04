package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.entity.BorekSale;


@Repository
public interface BorekSaleRepository extends JpaRepository<BorekSale, Long> {

	boolean existsByStoreIdAndBorekIdAndCustomerId(Long storeId, Long borekId, Long customerId);
	
	boolean existsByStoreIdAndBorekIdAndCustomerIdAndIdNot(Long storeId, Long borekId, Long customerId, Long excludedId);
	
	boolean existsByBorekId(Long borekId);
	
	boolean existsByBorekIdAndIdNot(Long borekId,Long exculededId);
	
	boolean existsByCustomerId(Long customerId);
	
}
