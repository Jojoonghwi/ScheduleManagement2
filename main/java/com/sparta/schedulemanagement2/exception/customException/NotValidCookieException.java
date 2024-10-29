package com.sparta.schedulemanagement2.exception.customException;

import com.sparta.schedulemanagement2.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotValidCookieException extends RuntimeException {
    public final ExceptionCode exceptionCode;

    public NotValidCookieException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
