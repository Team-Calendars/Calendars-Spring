package com.calendars.calendars.domain.review.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResponse {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewCreate {
        @NotEmpty
        private Long reviewId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewGetAllByCalendar {
        private List<ReviewInfo> reviews;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewInfo {
        @NotEmpty
        private Long memberId;
        @NotEmpty
        private Double rate;
        private String comment;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewDelete {
        @NotEmpty
        private Long reviewId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviewUpdate {
        @NotEmpty
        private Long reviewId;
    }
}
