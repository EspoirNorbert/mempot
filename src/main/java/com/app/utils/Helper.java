package com.app.utils;

import com.app.beans.RegisterRequest;
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
	
	public static String getDefautlAvatar(String firstname , String lastname) {
		return 
				"https://ui-avatars.com/api/?name=" + firstname + "+"+ lastname 
				+ "&background=2962ff&color=fff&size=100";
	}
}
