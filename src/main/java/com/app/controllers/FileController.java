package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.services.FilesStorageService;

@Controller
public class FileController {

	@Autowired
	private FilesStorageService filesStorageService;

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<?> getPdf(@PathVariable String filename) {
		   try {
			   Resource pdfFile = this.filesStorageService.load(filename);

			    HttpHeaders headers = new HttpHeaders();
			    headers.add(HttpHeaders.CONTENT_DISPOSITION, 
			    		"inline; filename=" + pdfFile.getFilename());

			   return ResponseEntity.ok()
				        .headers(headers)
				        .contentType(MediaType.APPLICATION_PDF)
				        .body(pdfFile);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
