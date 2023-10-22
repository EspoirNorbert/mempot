package com.app.utils;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MessageUtils {

	  public static void addFlashMessage(RedirectAttributes redirectAttributes, 
			  String messageType, String message) {
	        if (messageType != null && message != null) {
	            if(messageType.equals("success")) {
	            	redirectAttributes.addAttribute("success", message);
	            } 
	            
	            else if (messageType.equals("danger")) {
	            	redirectAttributes.addFlashAttribute("danger", message);
	            }
	        }
	    }
}
