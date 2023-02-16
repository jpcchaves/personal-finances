package com.jpcchaves.finances.domain.repository;

import com.jpcchaves.finances.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
     List<User> findByIdEmail(String email);
}
