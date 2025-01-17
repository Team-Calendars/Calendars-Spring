package com.calendars.calendars.domain.memberCalendar.repository;

import com.calendars.calendars.domain.memberCalendar.entity.MemberCalendar;
import com.calendars.calendars.domain.memberCalendar.service.MemberCalendarService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberCalendarRepository extends JpaRepository<MemberCalendar, Long> {
    Optional<MemberCalendar> findByCalendarId(Long calendarId);
}
