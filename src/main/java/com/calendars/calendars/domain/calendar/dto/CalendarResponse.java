package com.calendars.calendars.domain.calendar.dto;

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
        private Long calendarId;
    }
}
