package com.calendars.calendars.domain.calendar.controller;
import com.calendars.calendars.config.AuthenticationMember;
import com.calendars.calendars.domain.calendar.dto.CalendarRequest;
import com.calendars.calendars.domain.calendar.dto.CalendarResponse;
import com.calendars.calendars.domain.calendar.service.CalendarService;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendars")
@Tag(name = "캘린더 API", description = "캘린더 관련 멤버 API 입니다.")
public class CalendarController {

    private final CalendarService calendarService;


    @Operation(summary = "캘린더 생성 API", description = "캘린더 생성 API 입니다.")
    @PostMapping
    public BaseResponse<CalendarResponse.CalendarCreate> createCalendar(
            @AuthenticationMember Member member,
            @RequestBody CalendarRequest.CalendarCreate request) {
        return BaseResponse.onSuccess(calendarService.createCalendar(member, request));
    }


}
