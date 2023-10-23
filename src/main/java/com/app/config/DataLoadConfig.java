package com.app.config;

import org.springframework.stereotype.Component;

import com.app.models.Grade;
import com.app.models.Role;
import com.app.models.Sector;
import com.app.repositories.GradeRepository;
import com.app.repositories.RoleRepository;
import com.app.repositories.SectorRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
public class DataLoadConfig {

	private final RoleRepository roleRepository;
	private final GradeRepository gradeRepository;
	private final SectorRepository sectorRepository;

	public DataLoadConfig(RoleRepository roleRepository, GradeRepository gradeRepository, SectorRepository sectorRepository) {
		this.roleRepository = roleRepository;
		this.gradeRepository = gradeRepository;
		this.sectorRepository= sectorRepository;
	}

	@PostConstruct
	void loadData() {
		this.createUserAndRole();
		this.createGrade();
		this.createSector();
	}

	@Transactional
	void createUserAndRole() {

		if (roleRepository.findByName("ADMIN") == null) {
			Role adminRole = new Role("ADMIN");
			roleRepository.save(adminRole);	
		}

		if (roleRepository.findByName("USER") == null) {
			Role userRole = new Role("USER");
			roleRepository.save(userRole);	
		}
	}

	@Transactional
	void createGrade() {

		if (gradeRepository.findByName("Master") == null) {
			Grade master = new Grade("Master");
			gradeRepository.save(master);
		}

		if (gradeRepository.findByName("Licence") == null) {
			Grade licence = new Grade("Licence");
			gradeRepository.save(licence);
		}
	}

	@Transactional
	void createSector() {

		if (sectorRepository.findByName("Informatique") == null) {
			Sector informatique = new Sector("Informatique");
			sectorRepository.save(informatique);
		}

		if (sectorRepository.findByName("Medecine") == null) {
			Sector medecine = new Sector("Medecine");
			sectorRepository.save(medecine);
		}
	}

}
