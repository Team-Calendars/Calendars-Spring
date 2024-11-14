package com.calendars.calendars.domain.member.service;


import com.calendars.calendars.domain.member.entity.RefreshToken;
import com.calendars.calendars.domain.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken saveTokenInfo(String refreshToken, Long memberId) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(memberId)
                        .refreshToken(refreshToken)
                        .build()
        );
    }

    @Transactional
    public Optional<RefreshToken> findByMemberId(Long memberId) { // 만료된 accessToken으로 refreshToken을 찾아옴
        return refreshTokenRepository.findById(memberId);
    }

    @Transactional
    public void removeRefreshToken(String accessToken) { // Redis에 저장된 accessToken을 key값으로 하는 refreshToken 삭제
        refreshTokenRepository.findByAccessToken(accessToken)
                .ifPresent(refreshToken -> refreshTokenRepository.delete(refreshToken));
    }
}
