package com.calendars.calendars.domain.review.service;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.calendar.service.CalendarService;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.review.dto.ReviewRequest;
import com.calendars.calendars.domain.review.dto.ReviewResponse;
import com.calendars.calendars.domain.review.entity.Review;
import com.calendars.calendars.domain.review.mapper.ReviewMapper;
import com.calendars.calendars.domain.review.repository.ReviewRepository;
import com.calendars.calendars.global.RestApiException;
import com.calendars.calendars.global.errorCode.ReviewErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final CalendarService calendarService;

    @Override
    public Review findReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new RestApiException(ReviewErrorCode.REVIEW_NOT_FOUND));
    }

    public Review findReviewByCalendarAndMember(Long calendarId, Long memberId) {
        return reviewRepository.findByCalendarIdAndMemberId(calendarId, memberId).orElseThrow(() -> new RestApiException(ReviewErrorCode.REVIEW_NOT_FOUND));
    }

    @Override
    @Transactional
    public ReviewResponse.ReviewCreate createCalendar(Member member, Long calendarId, ReviewRequest.ReviewCreate request) {
        Calendar calendar = calendarService.findCalendar(calendarId);
        Optional<Review> existingReview = reviewRepository.findByCalendarIdAndMemberId(calendarId, member.getId());
        // 이미 리뷰가 존재한다면 새로 생성 못하게 막아야 한다.
        if (existingReview.isPresent()) {
            throw new RestApiException(ReviewErrorCode.REVIEW_ALREADY_EXIST);
        }
        Review review = reviewMapper.toReview(member, calendar, request.getRate(), request.getComment());
        reviewRepository.save(review);
        return reviewMapper.toReviewCreate(review);
    }

    @Override
    public ReviewResponse.ReviewGetAllByCalendar getAllReviewByCalendar(Member member, Long calendarId) {
//        Calendar calendar = calendarService.findCalendar(calendarId);
        List<Review> reviews = reviewRepository.findAllByCalendarId(calendarId);
        return reviewMapper.toReviewGetAllByCalendar(reviews);
    }

    @Override
    @Transactional
    public ReviewResponse.ReviewUpdate updateReview(Member member, Long reviewId, ReviewRequest.ReviewUpdate request) {
        Review review = findReview(reviewId);
        checkPermission(member, review);
        review.updateRate(request.getRate());
        if (request.getComment() != null) {
            review.updateComment(request.getComment());
        }
        return reviewMapper.toReviewUpdate(review);
    }

    private void checkPermission(Member member, Review review) {
        if (!member.getId().equals(review.getMember().getId())) {
            throw new RestApiException(ReviewErrorCode.REVIEW_NO_PERMISSION);
        }
    }

    @Override
    @Transactional
    public ReviewResponse.ReviewDelete deleteReview(Member member, Long reviewId) {
        Review review = findReview(reviewId);
        checkPermission(member, review);
        review.delete();
        return reviewMapper.toReviewDelete(review);
    }
}
