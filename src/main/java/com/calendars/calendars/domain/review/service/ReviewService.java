package com.calendars.calendars.domain.review.service;

import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.review.dto.ReviewRequest;
import com.calendars.calendars.domain.review.dto.ReviewResponse;
import com.calendars.calendars.domain.review.entity.Review;

public interface ReviewService {
    Review findReview(Long reviewId);

    ReviewResponse.ReviewCreate createReview(Member member, Long calendarId, ReviewRequest.ReviewCreate request);

    ReviewResponse.ReviewGetAllByCalendar getAllReviewByCalendar(Member member, Long calendarId);

    ReviewResponse.ReviewUpdate updateReview(Member member, Long reviewId, ReviewRequest.ReviewUpdate request);

    ReviewResponse.ReviewDelete deleteReview(Member member, Long reviewId);
}
