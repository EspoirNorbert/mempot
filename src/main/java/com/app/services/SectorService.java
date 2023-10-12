package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.Sector;
import com.app.repositories.SectorRepository;

@Service
public class SectorService {

	@Autowired
	private SectorRepository sectorRepository;
	
	public List<Sector> list() {
		return sectorRepository.findAll();
	}
	
	public void save(Sector grade) {
		sectorRepository.save(grade);
	}
	
	@Transactional(readOnly = true)
	public Sector findById(Integer id) {
		Sector grade = null;
		Optional<Sector> optionalGrade = sectorRepository.findById(id);
		
		if (optionalGrade.isPresent())
			grade = optionalGrade.get();
		
		return grade;
	}
	
	@Transactional(readOnly = true)
	public Sector findByName(String name) {
		return this.sectorRepository.findByName(name);
	}
	
	@Transactional
	public void delete (Integer id) {
		Sector grade = this.findById(id);
		if (grade != null)
			this.sectorRepository.delete(grade);
	}
}
