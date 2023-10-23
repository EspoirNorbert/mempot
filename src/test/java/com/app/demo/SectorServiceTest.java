package com.app.demo;


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
		if (sectorService.findByName("Informatique") == null) {
			Sector sectorInformatique = new Sector("Informatique");
			sectorService.save(sectorInformatique);
		}

		if (sectorService.findByName("Medecine") == null) {
			Sector sectorMedecine = new Sector("Medecine");
			sectorService.save(sectorMedecine);
		}
	}

	@Test
	void listAllSectors() {
		List<Sector> sectors = sectorService.list();
		if (sectors.size() == 0) {
			System.out.println("Aucun filiere recement pour le moment !");
		} else {
			for (Sector sector : sectors) {
				System.out.println(sector.getName());
			}
		}
	}
}
