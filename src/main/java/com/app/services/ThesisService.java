package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Thesis;
import com.app.repositories.ThesisRepository;

@Service
public class ThesisService {

	@Autowired
	private ThesisRepository thesisRepository;
	
	public List<Thesis> list() {
		return thesisRepository.findAll();
	}
	
	public void save(Thesis thesis) {
		thesisRepository.save(thesis);
	}
	
}
