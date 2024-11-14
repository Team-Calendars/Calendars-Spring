package com.calendars.calendars.config.security.service;

import com.calendars.calendars.config.security.CustomUserDetails;
import com.calendars.calendars.domain.member.repository.MemberRepository;
import com.calendars.calendars.global.RestApiException;
import com.calendars.calendars.global.errorCode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public CustomUserDetails loadUserByUsername(String memberId) {
        return memberRepository.findById(Long.valueOf(memberId))
                .map(CustomUserDetails::new).orElseThrow(
                        () -> new RestApiException(MemberErrorCode.MEMBER_NOT_FOUND)
                );
    }
}
