package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alirizakaygusuz.entity.StoreBorek;

public interface StoreBorekRepository extends JpaRepository<StoreBorek, Long> {
	boolean existsByStoreIdAndBorekId(Long storeId, Long borekId);

	boolean existsByStoreIdAndBorekIdAndIdNot(Long storeId, Long borekId, Long excludedId);
	
	
	boolean existsByBorekId(Long borekId);

	

}
