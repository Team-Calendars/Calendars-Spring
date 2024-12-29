package com.calendars.calendars.domain.calendar.service;

import com.calendars.calendars.domain.calendar.dto.CalendarRequest;
import com.calendars.calendars.domain.calendar.dto.CalendarResponse;
import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;

public interface CalendarService {
    Calendar findCalendar(Long calendarId);

    CalendarResponse.CalendarCreate createCalendar(Member member, CalendarRequest.CalendarCreate request);
}
