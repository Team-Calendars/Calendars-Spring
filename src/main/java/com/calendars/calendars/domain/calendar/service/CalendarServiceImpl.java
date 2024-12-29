package com.calendars.calendars.domain.calendar.service;


import com.calendars.calendars.domain.calendar.dto.CalendarRequest;
import com.calendars.calendars.domain.calendar.dto.CalendarResponse;
import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.calendar.mapper.CalendarMapper;
import com.calendars.calendars.domain.calendar.repository.CalendarRepository;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.member.service.MemberService;
import com.calendars.calendars.domain.memberCalendar.entity.CalendarPermission;
import com.calendars.calendars.domain.memberCalendar.entity.MemberCalendar;
import com.calendars.calendars.domain.memberCalendar.service.MemberCalendarService;
import com.calendars.calendars.global.RestApiException;
import com.calendars.calendars.global.errorCode.CalendarErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarMapper calendarMapper;
    private final MemberService memberService;
    private final MemberCalendarService memberCalendarService;

    @Override
    public Calendar findCalendar(Long calendarId) {
        return calendarRepository.findById(calendarId).orElseThrow(() -> new RestApiException(CalendarErrorCode.CALENDAR_NOT_FOUND));
    }
    @Override
    @Transactional
    public CalendarResponse.CalendarCreate createCalendar(Member member, CalendarRequest.CalendarCreate request) {
        Calendar calendar = calendarMapper.toCalendar(member, request.getTitle(), request.getDescription(), request.getCoverUrl(), request.getThumbnailUrl(), request.getThemeColor());
        calendarRepository.save(calendar);
        memberCalendarService.createMemberCalendar(member, calendar, CalendarPermission.MASTER);
        return calendarMapper.toCalendarCreate(calendar);
    }

    @Override
    public CalendarResponse.CalendarGetAll getAllCalendar(Member member) {
        List<Calendar> calendars = calendarRepository.findAll();
        return calendarMapper.toCalendarGetAll(calendars);
    }
}