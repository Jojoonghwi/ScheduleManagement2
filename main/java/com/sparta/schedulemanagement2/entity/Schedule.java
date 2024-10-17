package com.sparta.schedulemanagement2.entity;

import com.sparta.schedulemanagement2.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Table(name = "schedule") // 매핑할 테이블의 이름을 지정
@Getter
@Setter
@NoArgsConstructor
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "user_id", nullable = true)
//    private Long user_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "commentCount", nullable = false)
    private int commentCount;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Comment> CommentList = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<ScheduleUser> ScheduleUserList = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "author", nullable = false)
//    private User author;

//    @ManyToMany
//    @JoinTable(name = "write", // 중간 테이블 생성
//            joinColumns = @JoinColumn(name = "schedule_id"), // 현재 위치인 schedule Entity 에서 중간 테이블로 조인할 컬럼 설정
//            inverseJoinColumns = @JoinColumn(name = "user_id")) // 반대 위치인 User Entity 에서 중간 테이블로 조인할 컬럼 설정
//    private List<User> userList = new ArrayList<>();

    public Schedule(ScheduleRequestDto requestDto) {
        //this.author = author;
        //this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(ScheduleRequestDto requestDto) {
        //this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
