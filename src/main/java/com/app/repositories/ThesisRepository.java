package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Thesis;

public interface ThesisRepository extends JpaRepository<Thesis, Integer> {

	
}
