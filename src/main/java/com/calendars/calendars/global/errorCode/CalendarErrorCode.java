package com.calendars.calendars.global.errorCode;

import com.calendars.calendars.global.ErrorCode;
import com.calendars.calendars.global.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CalendarErrorCode implements ErrorCodeInterface {

    CALENDAR_NOT_FOUND("CALENDAR001", "Calendar가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    CALENDAR_NO_PERMISSION("CALENDAR002", "Calendar에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN);

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