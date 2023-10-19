package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.models.Thesis;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {

	List<Thesis> findFirst5ByOrderByIdDesc();
}
