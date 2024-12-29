package com.calendars.calendars.domain.memberCalendar.service;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.memberCalendar.dto.MemberCalendarResponse;
import com.calendars.calendars.domain.memberCalendar.entity.CalendarPermission;

public interface MemberCalendarService {
    MemberCalendarResponse.CreateMemberCalendar createMemberCalendar(Member member, Calendar calendar, CalendarPermission permission);

    MemberCalendarResponse.DeleteMemberCalendar deleteMemberCalendarByCalendar(Calendar calendar);
}
