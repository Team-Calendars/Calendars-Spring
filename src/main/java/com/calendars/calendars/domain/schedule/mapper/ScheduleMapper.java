package com.calendars.calendars.domain.schedule.mapper;

import com.calendars.calendars.domain.calendar.dto.CalendarResponse;
import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.schedule.dto.ScheduleResponse;
import com.calendars.calendars.domain.schedule.entity.Schedule;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ScheduleMapper {

    public Schedule toSchedule(Calendar calendar, String title, LocalDateTime startAt, LocalDateTime endAt, Boolean isRepeat, String linkUrl, String place, String note) {
        return Schedule.builder()
                .calendar(calendar)
                .title(title)
                .startAt(startAt)
                .endAt(endAt)
                .isRepeat(isRepeat)
                .linkUrl(linkUrl)
                .place(place)
                .note(note)
                .build();
    }

    public ScheduleResponse.ScheduleCreate toScheduleCreate(Schedule schedule) {
        return ScheduleResponse.ScheduleCreate.builder()
                .scheduleId(schedule.getId())
                .build();
    }

    public ScheduleResponse.ScheduleGetAll toScheduleGetAll(List<Schedule> schedules) {
        return ScheduleResponse.ScheduleGetAll.builder()
                .schedules(schedules.stream()
                        .map(schedule -> toScheduleInfo(schedule))
                        .toList())
                .build();
    }

    public ScheduleResponse.ScheduleInfo toScheduleInfo(Schedule schedule) {
        return ScheduleResponse.ScheduleInfo.builder()
                .masterId(schedule.getCalendar().getMaster().getId())
                .calendarId(schedule.getCalendar().getId())
                .scheduleId(schedule.getId())
                .title(schedule.getTitle())
                .startAt(schedule.getStartAt())
                .endAt(schedule.getEndAt())
                .isRepeat(schedule.getIsRepeat())
                .linkUrl(schedule.getLinkUrl())
                .place(schedule.getPlace())
                .note(schedule.getNote())
                .build();
    }

    public ScheduleResponse.ScheduleDelete toScheduleDelete(Schedule schedule) {
        return ScheduleResponse.ScheduleDelete.builder()
                .scheduleId(schedule.getId())
                .build();
    }

    public ScheduleResponse.ScheduleUpdate toScheduleUpdate(Schedule schedule) {
        return ScheduleResponse.ScheduleUpdate.builder()
                .scheduleId(schedule.getId())
                .build();
    }
}
