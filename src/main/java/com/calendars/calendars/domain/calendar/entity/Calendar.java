package com.calendars.calendars.domain.calendar.entity;

import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is null")
public class Calendar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calendar_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member master;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private Double reviewRateAverage;

    @Column
    private String coverUrl;

    @Column
    private String thumbnailUrl;

    @Column(nullable = false)
    private String themeColor;

    @Column(nullable = false)
    private Long scheduleCount;

    public void updateTitle(String title) { this.title = title; }
    public void updateDescription(String description) { this.description = description; }
    public void updateReviewRateAverage(Double reviewRateAverage) { this.reviewRateAverage = reviewRateAverage; }
    public void updateCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
    public void updateThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
    public void updateThemeColor(String themeColor) { this.themeColor = themeColor; }
    public void updateScheduleCount(Long scheduleCount) { this.scheduleCount = scheduleCount; }

}
