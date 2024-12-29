package com.calendars.calendars.domain.calendar.repository;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
