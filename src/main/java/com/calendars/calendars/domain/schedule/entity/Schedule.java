package com.calendars.calendars.domain.schedule.entity;

import com.calendars.calendars.domain.calendar.entity.Calendar;
import com.calendars.calendars.domain.member.entity.Member;
import com.calendars.calendars.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("deleted_at is null")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false)
    private Calendar calendar;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Boolean isRepeat;

    @Column
    private String linkUrl;

    @Column
    private String place;

    @Column
    private String note;

    public void updateTitle(String title) { this.title = title; }

    public void updateStartAt(LocalDateTime startAt) { this.startAt = startAt; }

    public void updateEndAt(LocalDateTime endAt) { this.endAt = endAt; }

    public void updateIsRepeat(Boolean isRepeat) { this.isRepeat = isRepeat; }

    public void updateLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }

    public void updatePlace(String place) { this.place = place; }

    public void updateNote(String note) { this.note = note; }

}