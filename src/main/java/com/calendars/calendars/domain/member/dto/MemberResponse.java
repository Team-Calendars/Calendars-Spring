package com.calendars.calendars.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberSignIn {
        private Long memberId;
        private String accessToken;
        private String refreshToken;
        private Boolean isServiced;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberTokens {
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberResign {
        private Long memberId;
    }

    @Getter
    @Builder
    public static class MemberId {
        @Schema(description = "회원의 고유 ID", example = "1")
        private Long memberId;
    }
}
