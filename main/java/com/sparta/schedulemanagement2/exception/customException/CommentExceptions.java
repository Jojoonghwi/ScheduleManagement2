package com.sparta.schedulemanagement2.exception.customException;

import com.sparta.schedulemanagement2.exception.enums.ExceptionCode;

import lombok.Getter;

@Getter
public class CommentExceptions extends RuntimeException {
	private final ExceptionCode exceptionCode;

	public CommentExceptions(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
}
