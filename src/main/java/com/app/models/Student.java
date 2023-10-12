package com.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "students")
public class Student extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 80)
	@NotEmpty(message = "Le matricule est obligatoire")
	private String matricule;
	
	@ManyToOne
	@JoinColumn(name = "sector_id")
	private Sector sector;
	
	@ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Thesis> thesis = new ArrayList<>();
	
	public Student() {}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMatricule() {
		return matricule;
	}
	
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
	public Grade getGrade() {
		return grade;
	}
	
	public Sector getSector() {
		return sector;
	}
	
	public void setSector(Sector sector) {
		this.sector = sector;
	}
}
