package com.sparta.schedulemanagement2.exception.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseExceptionCode {
    private String code;

    private String message;
}
