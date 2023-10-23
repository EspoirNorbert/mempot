package com.app.controllers;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomExceptionController extends AbstractErrorController {

	public CustomExceptionController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		HttpStatus  status = getStatus(request);
	   
	    if (status == HttpStatus.NOT_FOUND) {
	         return "error-404";
	    }
	    return "error";
	}
}
