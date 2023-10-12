package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

	Grade findByName(String name);
}
