package com.sparta.schedulemanagement2.exception.customException;

import com.sparta.schedulemanagement2.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class ScheduleExceptions extends RuntimeException {

	private final ExceptionCode exceptionCode;

	public ScheduleExceptions(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
