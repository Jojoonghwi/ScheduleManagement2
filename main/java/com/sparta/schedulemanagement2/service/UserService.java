package com.sparta.schedulemanagement2.service;

import com.sparta.schedulemanagement2.dto.UserRequestDto;
import com.sparta.schedulemanagement2.dto.UserResponseDto;
import com.sparta.schedulemanagement2.entity.Schedule;
import com.sparta.schedulemanagement2.entity.ScheduleUser;
import com.sparta.schedulemanagement2.entity.User;
import com.sparta.schedulemanagement2.repository.ScheduleUserRepository;
import com.sparta.schedulemanagement2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(UserRequestDto requestDto) {
        // RequestDto -> Entity
        User user = new User(requestDto);

        // DB 저장
        User saveUser = userRepository.save(user);

        // Entity -> ResponseDto
        UserResponseDto responseDto = new UserResponseDto(saveUser);

        return responseDto;
    }

//    private ScheduleUser createScheduleUser() {
//
//    }


    public List<UserResponseDto> getUser() {
        return userRepository.findAllByOrderByModifiedAtDesc().stream().map(UserResponseDto::new).toList();
    }

    @Transactional
    public Long updateUser(Long id, UserRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        User user = findUser(id);

        user.update(requestDto);

        return id;
    }

    public Long deleteUser(Long id) {
        // 해당 일정이 DB에 존재하는지 확인
        User user = findUser(id);

        userRepository.delete(user);

        return id;
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );
    }
}
