package com.app.utils;

import com.app.beans.RegisterRequest;
import com.app.beans.UpdateStudentRequest;
import com.app.models.Grade;
import com.app.models.Sector;
import com.app.models.Student;

public class Helper {

	public static String replaceSpaceByDash(String nomFichier) {
		return nomFichier.replaceAll(" ", "-").toLowerCase();
	}

	public static Student convertRegisterFormToStudent(RegisterRequest registerRequest) {

		if (registerRequest != null) {
			Student student = new Student();
			student.setFirstName(registerRequest.getFirstname());
			student.setLastname(registerRequest.getLastname());
			student.setMatricule(registerRequest.getMatricule());
			student.setEmail(registerRequest.getEmail());
			student.setPassword(registerRequest.getPassword());
			student.setGrade(new Grade(Long.parseLong(registerRequest.getGrade())));
			student.setSector(new Sector(Long.parseLong(registerRequest.getSector())));
			return student;
		}

		throw new NullPointerException("RegisterRequest object is null");
	}

	public static RegisterRequest convertStudentToRegisterForm(Student student) {

		if (student != null) {
			RegisterRequest registerRequest = new RegisterRequest();
			registerRequest.setFirstname(student.getFirstname());
			registerRequest.setLastname(student.getLastname());
			registerRequest.setMatricule(student.getMatricule());
			registerRequest.setEmail(student.getEmail());
			registerRequest.setPassword(registerRequest.getPassword());
			registerRequest.setGrade(student.getGrade().getId().toString());
			registerRequest.setSector(student.getSector().getId().toString());
			return registerRequest;
		}

		throw new NullPointerException("RegisterRequest object is null");
	}
	
	public static UpdateStudentRequest studentToUpdateStudentRequest (Student student) {
		

		if (student != null) {
			UpdateStudentRequest registerRequest = new UpdateStudentRequest();
			registerRequest.setId(student.getId());
			registerRequest.setFirstname(student.getFirstname());
			registerRequest.setLastname(student.getLastname());
			registerRequest.setMatricule(student.getMatricule());
			registerRequest.setEmail(student.getEmail());
			registerRequest.setAddress(student.getAddress());
			registerRequest.setGrade(student.getGrade().getId().toString());
			registerRequest.setSector(student.getSector().getId().toString());
			return registerRequest;
		}

		throw new NullPointerException("RegisterRequest object is null");
	}
	
	public static Student updateStudentRequestToStudent(UpdateStudentRequest updateStudentRequest) {

		if (updateStudentRequest != null) {
			Student student = new Student();
			student.setId(updateStudentRequest.getId());
			student.setFirstName(updateStudentRequest.getFirstname());
			student.setLastname(updateStudentRequest.getLastname());
			student.setMatricule(updateStudentRequest.getMatricule());
			student.setEmail(updateStudentRequest.getEmail());
			student.setAddress(updateStudentRequest.getAddress());
			student.setGrade(new Grade(Long.parseLong(updateStudentRequest.getGrade())));
			student.setSector(new Sector(Long.parseLong(updateStudentRequest.getSector())));
			return student;
		}

		throw new NullPointerException("UpdateStudentRequest object is null");
	}

	public static String getDefautlAvatar(String firstname , String lastname) {
		return 
				"https://ui-avatars.com/api/?name=" + firstname + "+"+ lastname 
				+ "&background=7a8194&color=fff&size=100";
	}
}
