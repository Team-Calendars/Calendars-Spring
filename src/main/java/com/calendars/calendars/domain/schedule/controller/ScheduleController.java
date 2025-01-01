package com.calendars.calendars.domain.schedule.controller;

import com.calendars.calendars.config.AuthenticationMember;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.schedule.dto.ScheduleRequest;
import com.calendars.calendars.domain.schedule.dto.ScheduleResponse;
import com.calendars.calendars.domain.schedule.service.ScheduleService;
import com.calendars.calendars.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
@Tag(name = "스케쥴 API", description = "스케쥴 관련 멤버 API 입니다.")
public class ScheduleController {

    private final ScheduleService scheduleService;


    @Operation(summary = "스케쥴 생성 API", description = "스케쥴 생성 API 입니다.")
    @PostMapping("/{calendarId}")
    public BaseResponse<ScheduleResponse.ScheduleCreate> createSchedule(
            @AuthenticationMember Member member,
            @PathVariable(name = "calendarId") Long calendarId,
            @RequestBody ScheduleRequest.ScheduleCreate request) {
        return BaseResponse.onSuccess(scheduleService.createSchedule(member, calendarId, request));
    }

    @Operation(summary = "(캘린더 별) 스케쥴 전체 조회 API", description = "(캘린더 별) 스케쥴 전체 조회 API 입니다.")
    @GetMapping("/{calendarId}")
    public BaseResponse<ScheduleResponse.ScheduleGetAll> getAllScheduleByCalendar(
            @AuthenticationMember Member member,
            @PathVariable(name = "calendarId") Long calendarId) {
        return BaseResponse.onSuccess(scheduleService.getAllScheduleByCalendar(member, calendarId));
    }

    @Operation(summary = "스케쥴 수정 API", description = "스케쥴 수정 API 입니다.")
    @PostMapping("/update/{calendarId}")
    public BaseResponse<ScheduleResponse.ScheduleUpdate> updateSchedule(
            @AuthenticationMember Member member,
            @RequestParam(name = "scheduleId") Long scheduleId,
            @RequestBody ScheduleRequest.ScheduleUpdate request) {
        return BaseResponse.onSuccess(scheduleService.updateSchedule(member, scheduleId, request));
    }

    @Operation(summary = "스케쥴 삭제 API", description = "스케쥴 삭제 API 입니다.")
    @DeleteMapping("{scheduleId}")
    public BaseResponse<ScheduleResponse.ScheduleDelete> deleteSchedule(
            @AuthenticationMember Member member,
            @PathVariable(name = "scheduleId") Long scheduleId) {
        return BaseResponse.onSuccess(scheduleService.deleteSchedule(member, scheduleId));
    }




}
