package com.calendars.calendars.domain.calendar.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CalendarRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CalendarCreate {

        @NotEmpty
        private String title;
        private String description;
        private String coverUrl;
        private String thumbnailUrl;
        @NotEmpty
        private String themeColor;
    }
}
