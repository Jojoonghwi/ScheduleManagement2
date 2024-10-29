package com.sparta.schedulemanagement2.exception.customException;

import com.sparta.schedulemanagement2.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class UserExceptions extends RuntimeException {
	private final ExceptionCode exceptionCode;

	public UserExceptions(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
