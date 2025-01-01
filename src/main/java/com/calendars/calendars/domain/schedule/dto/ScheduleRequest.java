package com.calendars.calendars.domain.schedule.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ScheduleRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScheduleCreate {
        @NotEmpty
        private String title;
        @NotEmpty
        private LocalDateTime startAt;
        @NotEmpty
        private LocalDateTime endAt;
        @NotEmpty
        private Boolean isRepeat;
        private String linkUrl;
        private String place;
        private String note;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScheduleUpdate {
        @NotEmpty
        private String title;
        @NotEmpty
        private LocalDateTime startAt;
        @NotEmpty
        private LocalDateTime endAt;
        @NotEmpty
        private Boolean isRepeat;
        private String linkUrl;
        private String place;
        private String note;
    }
}
