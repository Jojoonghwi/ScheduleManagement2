package com.sparta.schedulemanagement2.repository;

import com.sparta.schedulemanagement2.entity.ScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Long> {
}
