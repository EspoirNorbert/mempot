package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Sector;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

	Sector findByName(String name);
}
