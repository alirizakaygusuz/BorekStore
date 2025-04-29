package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.entity.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    boolean existsByAccountId(Long accountId);
    boolean existsByAccountIdAndIdNot(Long accountId, Long id);


}
