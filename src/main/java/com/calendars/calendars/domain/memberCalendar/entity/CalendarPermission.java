package com.calendars.calendars.domain.memberCalendar.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CalendarPermission {
    MASTER("관리자"),
    SUBSCRIBER("구독자");
    private final String toKorean;
}
