package com.calendars.calendars.domain.calendar.mapper;

import com.calendars.calendars.domain.calendar.dto.CalendarResponse;
import com.calendars.calendars.domain.calendar.entity.Calendar;
import org.springframework.stereotype.Component;

@Component
public class CalendarMapper {

    public Calendar toCalendar(String title, String description, Double reviewRateAverage, String coverUrl, String thumbnailUrl, String themeColor) {
        return Calendar.builder()
                .title(title)
                .description(description)
                .reviewRateAverage(reviewRateAverage)
                .coverUrl(coverUrl)
                .thumbnailUrl(thumbnailUrl)
                .themeColor(themeColor)
                .build();
    }

    public CalendarResponse.CalendarCreate toCalendarCreate(Calendar calendar) {
        return CalendarResponse.CalendarCreate.builder()
                .calendarId(calendar.getId())
                .build();
    }
}
