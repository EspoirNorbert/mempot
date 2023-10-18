package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;
import com.app.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired private StudentRepository studentRepository;
	@Autowired private SectorService sectorService;
	@Autowired private GradeService gradeService;

	public List<Student> list() {
		return studentRepository.findAll();
	}

	public Student findByMatricule(String matricule) {
		return this.studentRepository.findByMatricule(matricule);
	}

	@Transactional(readOnly = true)
	public Student findById(Long id) {
		Student student = null;
		Optional<Student> optionalStudent = studentRepository.findById(id);

		if (optionalStudent.isPresent())
			student = optionalStudent.get();

		return student;
	}

	public void update(Student student) {
		if (findById(student.getId()) != null) {

			// search sector
			Sector sector = sectorService.findById(student.getSector().getId());
			student.setSector(sector);

			// search grade
			Grade grade = gradeService.findById(student.getGrade().getId());
			student.setGrade(grade);
			
			// save
			studentRepository.save(student);
		}
	}

	@Transactional
	public void save(Student student) {
		studentRepository.save(student);
	}

	@Transactional
	public long count() {
		return studentRepository.count();
	}
}
