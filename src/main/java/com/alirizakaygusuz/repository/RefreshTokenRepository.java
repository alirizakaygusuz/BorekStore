package com.alirizakaygusuz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.entity.RefreshToken;
import com.alirizakaygusuz.entity.User;


@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	
	void deleteByUser(User user);
	
	Optional<RefreshToken>  findByRefreshToken(String refreshToken);
}
