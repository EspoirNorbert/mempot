package com.app.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.models.Sector;
import com.app.services.SectorService;

@SpringBootTest
class SectorServiceTest {

	@Autowired SectorService sectorService;
	 
	@Test
	void create() {
		Sector sectorInformatique = new Sector("Informatique");
		Sector sectorMedecine = new Sector("Medecine");
		sectorService.save(sectorInformatique);
		sectorService.save(sectorMedecine);
	}
	
	@Test
	void listAllSectors() {
		 List<Sector> sectors = sectorService.list();
		 assertEquals(2, sectors.size());
		 assertEquals("Informatique", sectors.get(0).getName());
	     assertEquals("Medecine", sectors.get(1).getName());
	}
}
