package com.sparta.schedulemanagement2.service;

import com.sparta.schedulemanagement2.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement2.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement2.entity.Schedule;
import com.sparta.schedulemanagement2.entity.ScheduleUser;
import com.sparta.schedulemanagement2.entity.User;
import com.sparta.schedulemanagement2.repository.CommentRepository;
import com.sparta.schedulemanagement2.repository.ScheduleRepository;
import com.sparta.schedulemanagement2.repository.ScheduleUserRepository;
import com.sparta.schedulemanagement2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleUserRepository scheduleUserRepository;


    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository, ScheduleUserRepository scheduleUserRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.scheduleUserRepository = scheduleUserRepository;
    }

    public ScheduleResponseDto createSchedule(Long userId, ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);
        ScheduleUser scheduleUser = new ScheduleUser();

        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        scheduleUser.setSchedule(saveSchedule);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        scheduleUser.setUser(user);


        scheduleUserRepository.save(scheduleUser);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleRepository = new ScheduleResponseDto(saveSchedule);

        return scheduleRepository;
    }

    public Page<ScheduleResponseDto> getSchedules(int page, int size, String sortBy, boolean isAsc) {
        // 페이징 처리
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Schedule> Schedules = scheduleRepository.findAll(pageable);

        return Schedules.map(ScheduleResponseDto::new);
    }

    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto, @PathVariable(required = false) Long manager) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        //관리자 업데이트
        if (manager != null) {        ScheduleUser scheduleUser = new ScheduleUser();
            scheduleUser.setSchedule(saveSchedule);
            User user = userRepository.findById(manager).orElseThrow(() -> new RuntimeException("User not found"));
            scheduleUser.setUser(user);
            scheduleUserRepository.save(scheduleUser);
        }


        schedule.update(requestDto);

        return id;

    }

    public Long deleteSchedule(Long id) {
        // 해당 일정이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        scheduleRepository.delete(schedule);

        return id;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );
    }

    private User findUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );
    }


}
