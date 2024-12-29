package com.calendars.calendars.domain.memberCalendar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberCalendarResponse {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateMemberCalendar {
        private Long memberCalendarId;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeleteMemberCalendar {
        private Long memberCalendarId;
    }
}
