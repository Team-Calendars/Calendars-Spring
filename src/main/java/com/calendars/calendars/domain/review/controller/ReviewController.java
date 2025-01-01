package com.calendars.calendars.domain.review.controller;

import com.calendars.calendars.config.AuthenticationMember;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.review.dto.ReviewRequest;
import com.calendars.calendars.domain.review.dto.ReviewResponse;
import com.calendars.calendars.domain.review.service.ReviewService;
import com.calendars.calendars.global.common.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Tag(name = "리뷰 API", description = "리뷰 관련 멤버 API 입니다.")
public class ReviewController {

    private final ReviewService reviewService;


    @Operation(summary = "리뷰 생성 API", description = "리뷰 생성 API 입니다.")
    @PostMapping("/{calendarId}")
    public BaseResponse<ReviewResponse.ReviewCreate> createCalendar(
            @AuthenticationMember Member member,
            @PathVariable(name = "calendarId") Long calendarId,
            @RequestBody ReviewRequest.ReviewCreate request) {
        return BaseResponse.onSuccess(reviewService.createReview(member, calendarId, request));
    }

    @Operation(summary = "(캘린더 별) 리뷰 조회 API", description = "(캘린더 별) 리뷰 조회 API 입니다.")
    @GetMapping("/{calendarId}")
    public BaseResponse<ReviewResponse.ReviewGetAllByCalendar> getAllReviewByCalendar(
            @AuthenticationMember Member member,
            @PathVariable(name = "calendarId") Long calendarId) {
        return BaseResponse.onSuccess(reviewService.getAllReviewByCalendar(member, calendarId));
    }

    @Operation(summary = "리뷰 수정 API", description = "리뷰 수정 API 입니다.")
    @PostMapping("/update")
    public BaseResponse<ReviewResponse.ReviewUpdate> updateReview(
            @AuthenticationMember Member member,
            @RequestParam(name = "reviewId") Long reviewId,
            @RequestBody ReviewRequest.ReviewUpdate request) {
        return BaseResponse.onSuccess(reviewService.updateReview(member, reviewId, request));
    }

    @Operation(summary = "리뷰 삭제 API", description = "리뷰 삭제 API 입니다.")
    @DeleteMapping("{reviewId}")
    public BaseResponse<ReviewResponse.ReviewDelete> deleteReview(
            @AuthenticationMember Member member,
            @PathVariable(name = "reviewId") Long reviewId) {
        return BaseResponse.onSuccess(reviewService.deleteReview(member, reviewId));
    }




}
