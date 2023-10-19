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
	
	public List<Student> getLatestGrades() {
		return studentRepository.findFirst5ByOrderByIdDesc();
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
		Student studentToFound = this.findById(student.getId());
		
		if (student != null) {

			// search sector
			Sector sector = sectorService.findById(student.getSector().getId());
			studentToFound.setSector(sector);

			// search grade
			Grade grade = gradeService.findById(student.getGrade().getId());
			studentToFound.setGrade(grade);
			
			// update some field
			studentToFound.setEmail(student.getEmail());
			studentToFound.setFirstName(student.getFirstname());
			studentToFound.setMatricule(student.getMatricule());
			studentToFound.setAddress(student.getAddress());
			
			System.out.println("New Student " + studentToFound);
			
			// save
			studentRepository.save(studentToFound);
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
