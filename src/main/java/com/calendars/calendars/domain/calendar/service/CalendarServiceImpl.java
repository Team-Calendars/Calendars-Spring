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

    @Override
    @Transactional
    public CalendarResponse.CalendarDelete deleteCalendar(Member member, Long calendarId) {
        Calendar calendar = findCalendar(calendarId);
        checkPermission(member, calendar);
        calendar.delete();
        memberCalendarService.deleteMemberCalendarByCalendar(calendar);
        return calendarMapper.toCalendarDelete(calendar);
    }

    @Override
    @Transactional
    public CalendarResponse.CalendarUpdate updateCalendar(Member member, Long calendarId, CalendarRequest.CalendarUpdate request) {
        Calendar calendar = findCalendar(calendarId);
        checkPermission(member, calendar);
        calendar.updateTitle(request.getTitle());
        calendar.updateDescription(request.getDescription());
        calendar.updateCoverUrl(request.getCoverUrl());
        calendar.updateThumbnailUrl(request.getThumbnailUrl());
        calendar.updateThemeColor(request.getThemeColor());
        return calendarMapper.toCalendarUpdate(calendar);
    }

    private void checkPermission(Member member, Calendar calendar) {
        if (!member.getId().equals(calendar.getMaster().getId())) {
            throw new RestApiException(CalendarErrorCode.CALENDAR_NO_PERMISSION);
        }
    }
}