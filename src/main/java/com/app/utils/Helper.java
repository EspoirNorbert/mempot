package com.app.utils;

public class Helper {

	public static String replaceSpaceByDash(String nomFichier) {
	    return nomFichier.replaceAll(" ", "-").toLowerCase();
	}
}
