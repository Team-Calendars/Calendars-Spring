package com.calendars.calendars.domain.member.service;

import com.calendars.calendars.config.jwt.dto.TokenInfo;
import com.calendars.calendars.config.jwt.service.JwtUtil;
import com.calendars.calendars.domain.member.dto.MemberRequest;
import com.calendars.calendars.domain.member.dto.MemberResponse;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.member.entity.PermissionRole;
import com.calendars.calendars.domain.member.entity.RefreshToken;
import com.calendars.calendars.domain.member.entity.SocialType;
import com.calendars.calendars.domain.member.mapper.AuthenticationMapper;
import com.calendars.calendars.domain.member.mapper.MemberMapper;
import com.calendars.calendars.domain.member.repository.MemberRepository;
import com.calendars.calendars.domain.member.repository.RefreshTokenRepository;
import com.calendars.calendars.global.RestApiException;
import com.calendars.calendars.global.errorCode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationMapper authenticationMapper;
    private final RefreshTokenRepository refreshTokenRepository;
    private SecretKey secretKey;

    @Override
    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new RestApiException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    public MemberResponse.MemberSignIn signIn(MemberRequest.MemberSignInByEncryptedUserIdentifier request) {

        String clientId = request.getEncryptedUserIdentifier();
        SocialType socialType = request.getSocialType();

        Optional<Member> optionalMember = memberRepository.findByClientId(clientId);

        // 1. 해당 유저가 존재하지 않으면 : Member 객체 생성하고 DB에 저장 -> 회원가입
        if (optionalMember.isEmpty()) {
            // TODO: 새로운 유저를 만들고 디비에 저장 & JWT Token 생성
            return saveNewMember(clientId, socialType);
        }
        // 2. 해당 유저가 존재한다면 : Member 객체를 DB에서 불러오고, MemberSignIn response 반환
        // TODO: 토큰 유효 시간을 검사해서 accessToken 또는 refreshToken의 유효 기간이 만료되었을 때, 만료된 토큰을 각각 재발급해주는 로직 구현
        boolean isServiced = optionalMember.get().getClientId() != null;

        Member member = optionalMember.get();
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findById(optionalMember.get().getId());
        String refreshToken = optionalRefreshToken.get().getRefreshToken();
        String accessToken = optionalRefreshToken.get().getAccessToken();

        if (jwtUtil.isExpired(accessToken)) {
            if (jwtUtil.isExpired(refreshToken)) {
                return generateNewToken(member, isServiced);
            }
            String newAccessToken = jwtUtil.createAccessToken(member.getId(), member.getClientId(),
                    member.getPermissionRole());
            TokenInfo tokenInfo = authenticationMapper.toTokenInfo(newAccessToken, refreshToken);

            refreshTokenRepository.save(new RefreshToken(member.getId(), refreshToken, newAccessToken));
            return memberMapper.toMemberSignIn(member, tokenInfo, isServiced);
        }

        TokenInfo tokenInfo = authenticationMapper.toTokenInfo(accessToken, refreshToken);
        return memberMapper.toMemberSignIn(member, tokenInfo, isServiced);
    }

    @Override
    public MemberResponse.MemberResign resign(Member member) {
        Member resignMember = findById(member.getId());
        resignMember.resign();
        return memberMapper.toMemberResign(member);
    }


    //
    private MemberResponse.MemberSignIn saveNewMember(String clientId, SocialType socialType) {
        Member member = memberMapper.toMember(clientId, socialType);
        Member newMember = memberRepository.save(member);

        return generateNewToken(newMember, false);
    }

    private MemberResponse.MemberSignIn generateNewToken(Member member, Boolean isServiced) {
        Long memberId = member.getId();
        String clientId = member.getClientId();
        PermissionRole permissionRole = member.getPermissionRole();

        MemberResponse.MemberTokens memberTokens = jwtUtil.refreshTokens(memberId, clientId, permissionRole);

        TokenInfo tokenInfo = authenticationMapper.toTokenInfo(memberTokens.getAccessToken(), memberTokens.getRefreshToken());

        return memberMapper.toMemberSignIn(member, tokenInfo, isServiced);
    }
}



