package com.calendars.calendars.domain.review.repository;

import com.calendars.calendars.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCalendarId(Long calendarId);

    Optional<Review> findByCalendarIdAndMemberId(Long calendarId, Long memberId);
}
