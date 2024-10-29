package com.sparta.schedulemanagement2.exception.customException;

import com.sparta.schedulemanagement2.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class NotValidTokenException extends RuntimeException {
    private final ExceptionCode exceptionCode;

    public NotValidTokenException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
