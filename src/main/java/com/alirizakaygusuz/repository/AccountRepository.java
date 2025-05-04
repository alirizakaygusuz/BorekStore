package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	boolean existsByCardNo(String cardNo);
	
	boolean existsByCardNoAndIdNot(String cardNo ,  Long id);
	
	boolean existsByIdentityNumber(String identityNumber);
	
	boolean existsByIdentityNumberAndIdNot(String identityNumber , Long id);
	
	boolean existsByAddressIdAndIdNot(Long addressId,Long id);
	
	boolean existsByAddressId(Long addressId);
}
