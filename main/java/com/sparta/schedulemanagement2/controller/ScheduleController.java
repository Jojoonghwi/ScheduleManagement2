package com.sparta.schedulemanagement2.controller;

import com.sparta.schedulemanagement2.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement2.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement2.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("{id}")
    public ScheduleResponseDto createSchedule(@Valid @PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(id, requestDto);
    }

    @GetMapping("")
    public Page<ScheduleResponseDto> getSchedules(
            @RequestParam("page") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return scheduleService.getSchedules(page-1, size, "modifiedAt", false);
    }

    //관리자 업데이트
    @PutMapping( {"{scheduleId}", "{scheduleId}/{optionalId}"})
    public Long updateSchedule(@PathVariable Long scheduleId,
                               @PathVariable(required = false) Long optionalId,
                               @RequestBody ScheduleRequestDto requestDto) {


        return scheduleService.updateSchedule(scheduleId, requestDto, optionalId);
    }

    @DeleteMapping("{id}")
    public Long deleteSchedule(@PathVariable Long id) {
        return scheduleService.deleteSchedule(id);
    }
}
