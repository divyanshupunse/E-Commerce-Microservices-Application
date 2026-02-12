package com.divyanshu.user_service.user_service_repository;

import com.divyanshu.user_service.user_service_model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
