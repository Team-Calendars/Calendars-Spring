package com.calendars.calendars.domain.memberCalendar.service;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.calendar.service.CalendarService;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.member.service.MemberService;
import com.calendars.calendars.domain.memberCalendar.dto.MemberCalendarResponse;
import com.calendars.calendars.domain.memberCalendar.entity.CalendarPermission;
import com.calendars.calendars.domain.memberCalendar.entity.MemberCalendar;
import com.calendars.calendars.domain.memberCalendar.mapper.MemberCalendarMapper;
import com.calendars.calendars.domain.memberCalendar.repository.MemberCalendarRepository;
import com.calendars.calendars.global.errorCode.MemberCalendarErrorCode;
import com.calendars.calendars.global.errorCode.MemberErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCalendarServiceImpl implements MemberCalendarService {

    private final MemberCalendarMapper memberCalendarMapper;
    private final MemberCalendarRepository memberCalendarRepository;

    @Override
    public MemberCalendarResponse.CreateMemberCalendar createMemberCalendar(Member member, Calendar calendar, CalendarPermission calendarPermission) {
        MemberCalendar memberCalendar = memberCalendarMapper.toMemberCalendar(member, calendar, calendarPermission);

        memberCalendarRepository.save(memberCalendar);
        return memberCalendarMapper.toCreateMemberCalendar(memberCalendar);
    }

    @Override
    public MemberCalendarResponse.DeleteMemberCalendar deleteMemberCalendarByCalendar(Calendar calendar) {
        MemberCalendar memberCalendar = findMemberCalendarByCalendarId(calendar.getId());
        memberCalendar.delete();
        return memberCalendarMapper.toDeleteMemberCalender(memberCalendar);
    }

    private MemberCalendar findMemberCalendarByCalendarId(Long calendarId) {
        MemberCalendar memberCalendar = memberCalendarRepository.findByCalendarId(calendarId).orElseThrow(
                () -> new RuntimeException(MemberCalendarErrorCode.MEMBER_CALENDAR_NOT_FOUND.getCode()));
        return memberCalendar;
    }

}
