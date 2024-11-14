package com.calendars.calendars.domain.member.repository;

import com.calendars.calendars.domain.member.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByAccessToken(String accessToken);
}
