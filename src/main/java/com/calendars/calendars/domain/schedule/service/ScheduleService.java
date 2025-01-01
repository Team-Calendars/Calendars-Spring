package com.calendars.calendars.domain.schedule.service;

import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.schedule.dto.ScheduleRequest;
import com.calendars.calendars.domain.schedule.dto.ScheduleResponse;
import com.calendars.calendars.domain.schedule.entity.Schedule;

public interface ScheduleService {
    Schedule findSchedule(Long scheduleId);

    ScheduleResponse.ScheduleCreate createSchedule(Member member, Long calendarId, ScheduleRequest.ScheduleCreate request);

    ScheduleResponse.ScheduleGetAll getAllScheduleByCalendar(Member member, Long calendarId);

    ScheduleResponse.ScheduleDelete deleteSchedule(Member member, Long scheduleId);

    ScheduleResponse.ScheduleUpdate updateSchedule(Member member, Long scheduleId, ScheduleRequest.ScheduleUpdate request);
}
