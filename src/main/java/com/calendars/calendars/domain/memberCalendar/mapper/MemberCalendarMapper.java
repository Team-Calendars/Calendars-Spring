package com.calendars.calendars.domain.memberCalendar.mapper;


import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.memberCalendar.dto.MemberCalendarResponse;
import com.calendars.calendars.domain.memberCalendar.entity.CalendarPermission;
import com.calendars.calendars.domain.memberCalendar.entity.MemberCalendar;
import org.springframework.stereotype.Component;

@Component
public class MemberCalendarMapper {

    public MemberCalendar toMemberCalendar(Member member, Calendar calendar, CalendarPermission calendarPermission) {
        return MemberCalendar.builder()
                .member(member)
                .calendar(calendar)
                .calendarPermission(calendarPermission)
                .build();
    }

    public MemberCalendarResponse.CreateMemberCalendar toCreateMemberCalendar(MemberCalendar memberCalendar) {
        return MemberCalendarResponse.CreateMemberCalendar.builder()
                .memberCalendarId(memberCalendar.getId())
                .build();
    }

    public MemberCalendarResponse.DeleteMemberCalendar toDeleteMemberCalender(MemberCalendar memberCalendar) {
        return MemberCalendarResponse.DeleteMemberCalendar.builder()
                .memberCalendarId(memberCalendar.getId())
                .build();
    }
}
