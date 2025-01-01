package com.calendars.calendars.domain.schedule.service;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.calendar.service.CalendarService;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.member.service.MemberService;
import com.calendars.calendars.domain.schedule.dto.ScheduleRequest;
import com.calendars.calendars.domain.schedule.dto.ScheduleResponse;
import com.calendars.calendars.domain.schedule.entity.Schedule;
import com.calendars.calendars.domain.schedule.mapper.ScheduleMapper;
import com.calendars.calendars.domain.schedule.repository.ScheduleRepository;
import com.calendars.calendars.global.RestApiException;
import com.calendars.calendars.global.errorCode.ScheduleErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final MemberService memberService;
    private final CalendarService calendarService;

    @Override
    public Schedule findSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(() -> new RestApiException(ScheduleErrorCode.SCHEDULE_NOT_FOUND));
    }
    @Override
    @Transactional
    public ScheduleResponse.ScheduleCreate createSchedule(Member member, Long calendarId, ScheduleRequest.ScheduleCreate request) {
        Calendar calendar = calendarService.findCalendar(calendarId);
        // 캘린더에 생성에 대한 권한 체크
        checkPermission(member, calendar);
        Schedule schedule = scheduleMapper.toSchedule(calendar, request.getTitle(), request.getStartAt(), request.getEndAt(), request.getIsRepeat(), request.getLinkUrl(), request.getPlace(), request.getNote());
        scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleCreate(schedule);
    }

    @Override
    public ScheduleResponse.ScheduleGetAll getAllScheduleByCalendar(Member member, Long calendarId) {
        calendarService.findCalendar(calendarId);
        List<Schedule> schedules = scheduleRepository.findAllByCalendarId(calendarId);
        return scheduleMapper.toScheduleGetAll(schedules);
    }

    @Override
    @Transactional
    public ScheduleResponse.ScheduleDelete deleteSchedule(Member member, Long scheduleId) {
        Schedule schedule = findSchedule(scheduleId);
        Calendar calendar = calendarService.findCalendar(schedule.getCalendar().getId());
        checkPermission(member, calendar);
        schedule.delete();
        return scheduleMapper.toScheduleDelete(schedule);
    }

    @Override
    @Transactional
    public ScheduleResponse.ScheduleUpdate updateSchedule(Member member, Long scheduleId, ScheduleRequest.ScheduleUpdate request) {
        Schedule schedule = findSchedule(scheduleId);
        Calendar calendar = calendarService.findCalendar(schedule.getCalendar().getId());
        // 캘린더에 수정에 대한 권한 체크
        checkPermission(member, calendar);
        schedule.updateTitle(request.getTitle());
        schedule.updateStartAt(request.getStartAt());
        schedule.updateEndAt(request.getStartAt());
        schedule.updateIsRepeat(request.getIsRepeat());
        schedule.updateLinkUrl(request.getLinkUrl());
        schedule.updatePlace(request.getPlace());
        schedule.updateNote(request.getNote());
        return scheduleMapper.toScheduleUpdate(schedule);
    }

    private void checkPermission(Member member, Calendar calendar) {
        if (!member.getId().equals(calendar.getMaster().getId())) {
            throw new RestApiException(ScheduleErrorCode.SCHEDULE_NO_PERMISSION);
        }
    }
}