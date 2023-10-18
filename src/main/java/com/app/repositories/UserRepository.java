package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
