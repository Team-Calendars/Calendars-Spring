package com.calendars.calendars.domain.calendar.mapper;

import com.calendars.calendars.domain.calendar.dto.CalendarResponse;
import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CalendarMapper {

    public Calendar toCalendar(Member member, String title, String description, String coverUrl, String thumbnailUrl, String themeColor) {
        return Calendar.builder()
                .master(member)
                .title(title)
                .description(description)
                .reviewRateAverage(0D)
                .coverUrl(coverUrl)
                .thumbnailUrl(thumbnailUrl)
                .themeColor(themeColor)
                .scheduleCount(0L)
                .build();
    }

    public CalendarResponse.CalendarCreate toCalendarCreate(Calendar calendar) {
        return CalendarResponse.CalendarCreate.builder()
                .calendarId(calendar.getId())
                .build();
    }

    public CalendarResponse.CalendarGetAll toCalendarGetAll(List<Calendar> calendars) {
        return CalendarResponse.CalendarGetAll.builder()
                .calendars(calendars.stream()
                        .map(calendar -> toCalendarInfo(calendar))
                        .toList())
                .build();
    }

    public CalendarResponse.CalendarInfo toCalendarInfo(Calendar calendar) {
        return CalendarResponse.CalendarInfo.builder()
                .masterId(calendar.getMaster().getId())
                .calendarId(calendar.getId())
                .title(calendar.getTitle())
                .description(calendar.getDescription())
                .reviewRateAverage(calendar.getReviewRateAverage())
                .coverUrl(calendar.getCoverUrl())
                .thumbnailUrl(calendar.getThumbnailUrl())
                .themeCount(calendar.getThemeColor())
                .scheduleCount(calendar.getScheduleCount())
                .build();
    }

    public CalendarResponse.CalendarDelete toCalendarDelete(Calendar calendar) {
        return CalendarResponse.CalendarDelete.builder()
                .calendarId(calendar.getId())
                .build();
    }

    public CalendarResponse.CalendarUpdate toCalendarUpdate(Calendar calendar) {
        return CalendarResponse.CalendarUpdate.builder()
                .calendarId(calendar.getId())
                .build();
    }
}
