package com.calendars.calendars.domain.review.mapper;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.domain.review.dto.ReviewResponse;
import com.calendars.calendars.domain.review.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReviewMapper {
    public static Review toReview(Member member, Calendar calendar, Double rate, String comment) {
        return Review.builder()
                .member(member)
                .calendar(calendar)
                .rate(rate)
                .comment(comment)
                .build();
    }

    public static ReviewResponse.ReviewCreate toReviewCreate(Review review) {
        return ReviewResponse.ReviewCreate.builder()
                .reviewId(review.getId())
                .build();
    }

    public ReviewResponse.ReviewGetAllByCalendar toReviewGetAllByCalendar(List<Review> reviews) {
        return ReviewResponse.ReviewGetAllByCalendar.builder()
                .reviews(reviews.stream()
                        .map(review -> toReviewInfo(review))
                        .toList())
                .build();
    }

    private ReviewResponse.ReviewInfo toReviewInfo(Review review) {
        return ReviewResponse.ReviewInfo.builder()
                .memberId(review.getMember().getId())
                .rate(review.getRate())
                .comment(review.getComment())
                .build();
    }

    public ReviewResponse.ReviewDelete toReviewDelete(Review review) {
        return ReviewResponse.ReviewDelete.builder()
                .reviewId(review.getId())
                .build();
    }

    public ReviewResponse.ReviewUpdate toReviewUpdate(Review review) {
        return ReviewResponse.ReviewUpdate.builder()
                .reviewId(review.getId())
                .build();
    }
}
