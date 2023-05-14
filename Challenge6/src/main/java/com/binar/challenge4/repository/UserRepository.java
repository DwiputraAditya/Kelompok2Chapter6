package com.binar.challenge4.repository;

import com.binar.challenge4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
