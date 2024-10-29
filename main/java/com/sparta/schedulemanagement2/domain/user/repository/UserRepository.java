package com.sparta.schedulemanagement2.domain.user.repository;

import com.sparta.schedulemanagement2.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByModifiedAtDesc();
    Optional<User> findByEmail(String email);
    List<User> findByRole(String role);
}
