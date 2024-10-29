package com.sparta.schedulemanagement2.domain.scheduleUser.repository;

import java.util.Optional;

import com.sparta.schedulemanagement2.domain.scheduleUser.entity.ScheduleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sparta.schedulemanagement2.domain.schedule.entity.Schedule;

public interface ScheduleUserRepository extends JpaRepository<ScheduleUser, Long> {
	Optional<ScheduleUser> findByScheduleIdAndUserId(Long scheduleId, Long userId);
}
