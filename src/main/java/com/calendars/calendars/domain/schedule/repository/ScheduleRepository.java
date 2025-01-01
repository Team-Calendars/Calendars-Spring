package com.calendars.calendars.domain.schedule.repository;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByCalendarId(Long calendarId);
}
