package com.sparta.schedulemanagement2.service;

import com.sparta.schedulemanagement2.dto.CommentRequestDto;
import com.sparta.schedulemanagement2.dto.CommentResponseDto;
import com.sparta.schedulemanagement2.entity.Comment;
import com.sparta.schedulemanagement2.entity.Schedule;
import com.sparta.schedulemanagement2.repository.CommentRepository;
import com.sparta.schedulemanagement2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto) {
        // RequestDto -> Entity
        Comment comment = new Comment(requestDto);

        //입력받은 일정의 아이디가 있는지 확인 후 저장
        Schedule schedule = findSchedule(scheduleId);
        comment.setSchedule(schedule);

        // DB 저장
        Comment saveComment = commentRepository.save(comment);

        // Entity -> ResponseDto
        CommentResponseDto CommentRepository = new CommentResponseDto(saveComment);

        return CommentRepository;
    }

    public List<CommentResponseDto> getComments() {
        // DB 조회
        return commentRepository.findAllByOrderByModifiedAtDesc().stream().map(CommentResponseDto::new).toList();
    }

    @Transactional
    public Long updateComment(Long id, CommentRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Comment comment = findComment(id);

        comment.update(requestDto);

        return id;

    }

    public Long deleteComment(Long id) {
        // 해당 일정이 DB에 존재하는지 확인
        Comment comment = findComment(id);

        commentRepository.delete(comment);

        return id;
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다")
        );
    }
}
