package com.calendars.calendars.domain.member.dto;

import com.calendars.calendars.domain.member.entity.SocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberSignInByEncryptedUserIdentifier {
        @Schema(description = "암호화된 사용자 식별자", defaultValue = "encryptedUserId123")
        private String encryptedUserIdentifier;

        @Schema(description = "소셜 타입", defaultValue = "APPLE")
        private SocialType socialType;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberUpdateMyProfile {
        private String name;
        private String phoneNumber;
        private String email;
    }
}
