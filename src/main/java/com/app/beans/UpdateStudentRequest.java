package com.app.beans;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;

public class UpdateStudentRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotEmpty(message = "Le prenom est obligatoire")
	private String firstname;
	
	@NotEmpty(message = "Le nom est obligatoire")
	private String lastname;
	
	@NotEmpty(message = "Le matricule est obligatoire")
	private String matricule;
	
	@NotEmpty(message = "La filiere est obligatoire")
	private String sector;
	
	@NotEmpty(message = "Le niveau est obligatoire")
	private String grade;
	
	@NotEmpty(message = "L'email est obligatoire")
	private String email;
	
	private String address;
	
	public UpdateStudentRequest() {}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "RegisterRequest [firstname=" + firstname + ", lastname=" + lastname + ", matricule=" + matricule
				+ ", sector=" + sector + ", grade=" + grade + ", email=" + email + "]";
	}
}
