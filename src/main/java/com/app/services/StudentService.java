package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Student;
import com.app.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> list() {
		return studentRepository.findAll();
	}
	
	public void save(Student student) {
		studentRepository.save(student);
	}
	
}
