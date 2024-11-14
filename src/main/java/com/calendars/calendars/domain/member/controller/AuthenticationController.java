package com.calendars.calendars.domain.member.controller;


import com.calendars.calendars.config.AuthenticationMember;
import com.calendars.calendars.domain.member.dto.MemberRequest;
import com.calendars.calendars.domain.member.dto.MemberResponse;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.member.service.MemberService;
import com.calendars.calendars.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "(로그인) 멤버 API", description = "로그인 관련 멤버 API 입니다.")
public class AuthenticationController {

    private final MemberService memberService;


    @Operation(summary = "로그인 API", description = "로그인 API 입니다.")
    @PostMapping("/sign-in")
    public BaseResponse<MemberResponse.MemberSignIn> signIn(
            @RequestBody MemberRequest.MemberSignInByEncryptedUserIdentifier request) {
        return BaseResponse.onSuccess(memberService.signIn(request));
    }

    @Operation(summary = "회원 탈퇴 API", description = "회원 탈퇴 API 입니다.")
    @GetMapping("/resign")
    public BaseResponse<MemberResponse.MemberResign> resign(
            @AuthenticationMember Member member) {
        return BaseResponse.onSuccess(memberService.resign(member));
    }

}
