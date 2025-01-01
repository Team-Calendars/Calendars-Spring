package com.calendars.calendars.domain.schedule.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ScheduleCreate {
        @NotEmpty
        private Long scheduleId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ScheduleGetAll {
        private List<ScheduleInfo> schedules;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ScheduleInfo {
        @NotEmpty
        private Long masterId;
        @NotEmpty
        private Long calendarId;
        @NotEmpty
        private Long scheduleId;
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
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ScheduleDelete {
        @NotEmpty
        private Long scheduleId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ScheduleUpdate {
        @NotEmpty
        private Long scheduleId;
    }
}
