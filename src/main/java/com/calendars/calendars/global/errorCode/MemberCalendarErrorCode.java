package com.calendars.calendars.global.errorCode;

import com.calendars.calendars.global.ErrorCode;
import com.calendars.calendars.global.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberCalendarErrorCode implements ErrorCodeInterface {

    MEMBER_CALENDAR_NOT_FOUND("MEMBERCALENDAR001", "MemberCalendar가 존재하지 않습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.builder()
                .code(code)
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }
}