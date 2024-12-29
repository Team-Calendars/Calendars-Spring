package com.calendars.calendars.domain.member.service;


import com.calendars.calendars.domain.member.dto.MemberRequest;
import com.calendars.calendars.domain.member.dto.MemberResponse;
import com.calendars.calendars.domain.member.entity.Member;

public interface MemberService {

    Member findMember(Long memberId);

    MemberResponse.MemberSignIn signIn(MemberRequest.MemberSignInByEncryptedUserIdentifier request);

    MemberResponse.MemberResign resign(Member member);

}
