package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.models.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {

	Sector findByName(String name);
	List<Sector> findFirst5ByOrderByIdDesc();
}
