package com.sparta.schedulemanagement2.controller;

import com.sparta.schedulemanagement2.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement2.dto.UserRequestDto;
import com.sparta.schedulemanagement2.dto.UserResponseDto;
import com.sparta.schedulemanagement2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    @GetMapping("")
    public Page<UserResponseDto> getComments(
            @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return userService.getUser(page-1, size, "modifiedAt", false);
    }



    @PutMapping("{id}")
    public Long updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.updateUser(id, requestDto);
    }

    @DeleteMapping("{id}")
    public Long deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
