package com.ismaildurcan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ismaildurcan.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

}
