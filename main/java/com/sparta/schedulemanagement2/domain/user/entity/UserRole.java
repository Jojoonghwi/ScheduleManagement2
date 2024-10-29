package com.sparta.schedulemanagement2.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN("ADMIN"),
	USER("USER");

	private String role;

	UserRole(String role) {
		this.role = role;
	}
}
