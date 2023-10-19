package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByMatricule(String matricule);
	List<Student> findFirst5ByOrderByIdDesc();
}
