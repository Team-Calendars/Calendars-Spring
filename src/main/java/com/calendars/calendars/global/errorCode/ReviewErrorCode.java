package com.calendars.calendars.global.errorCode;

import com.calendars.calendars.global.ErrorCode;
import com.calendars.calendars.global.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ErrorCodeInterface {

    REVIEW_NOT_FOUND("REVIEW001", "Review가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    REVIEW_NO_PERMISSION("REVIEW002", "Review에 대한 권한이 없습니다.", HttpStatus.FORBIDDEN),
    REVIEW_ALREADY_EXIST("REVIEW003", "이미 Review가 존재합니다.", HttpStatus.BAD_REQUEST),
    ;

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