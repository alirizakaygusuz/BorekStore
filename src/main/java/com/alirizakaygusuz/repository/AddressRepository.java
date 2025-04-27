package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.entity.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	

}
