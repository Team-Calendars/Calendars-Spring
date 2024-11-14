package com.calendars.calendars.domain.member.mapper;

import com.calendars.calendars.config.jwt.dto.TokenInfo;
import com.calendars.calendars.domain.member.dto.MemberResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {
    public TokenInfo toTokenInfo(String accessToken, String refreshToken) {
        return TokenInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public MemberResponse.MemberTokens toMemberTokens(String accessToken, String refreshToken) {
        return MemberResponse.MemberTokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
