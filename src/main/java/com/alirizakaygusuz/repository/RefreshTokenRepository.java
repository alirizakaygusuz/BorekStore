package com.alirizakaygusuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.entity.RefreshToken;
import com.alirizakaygusuz.entity.User;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	void deleteByUser(User user);
}
