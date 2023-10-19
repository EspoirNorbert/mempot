package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

	Grade findByName(String name);
	List<Grade> findFirst5ByOrderByIdDesc();
}
