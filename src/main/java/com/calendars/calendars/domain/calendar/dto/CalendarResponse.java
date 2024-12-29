package com.calendars.calendars.domain.calendar.dto;

import com.calendars.calendars.domain.member.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

public class CalendarResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CalendarCreate {
        @NotEmpty
        private Long calendarId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CalendarGetAll {
        private List<CalendarInfo> calendars;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CalendarInfo {
        @NotEmpty
        private Long masterId;
        @NotEmpty
        private Long calendarId;
        @NotEmpty
        private String title;
        private String description;
        @NotEmpty
        private Double reviewRateAverage;
        private String coverUrl;
        @NotEmpty
        private String thumbnailUrl;
        @NotEmpty
        private String themeCount;
        @NotEmpty
        private Long scheduleCount;
    }

}
