package com.calendars.calendars.domain.member.mapper;

import com.calendars.calendars.config.jwt.dto.TokenInfo;
import com.calendars.calendars.domain.member.dto.MemberResponse;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.member.entity.PermissionRole;
import com.calendars.calendars.domain.member.entity.SocialType;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberResponse.MemberId toMemberId(Long memberId) {
        return MemberResponse.MemberId.builder()
                .memberId(memberId)
                .build();
    }
    public MemberResponse.MemberSignIn toMemberSignIn(final Member member, TokenInfo tokenInfo, Boolean isServiced) {
        return MemberResponse.MemberSignIn.builder()
                .memberId(member.getId())
                .isServiced(isServiced)
                .accessToken(tokenInfo.getAccessToken())
                .refreshToken(tokenInfo.getRefreshToken())
                .build();
    }

    public Member toMember(final String clientId, SocialType socialType) {
        return Member.builder()
                .clientId(clientId)
                .nickname("")
                .socialType(socialType)
                .permissionRole(PermissionRole.ADMIN)
                .build();
    }

    public MemberResponse.MemberResign toMemberResign(Member member) {
        return MemberResponse.MemberResign.builder()
                .memberId(member.getId())
                .build();
    }
}
