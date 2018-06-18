package com.company;

import java.util.Random;

public class ErrorAdding implements Error {
	public String make(String str) {
		int resRandom;
		do {
			resRandom = StringRandomPositionFinder.find(str);
		} while (str.charAt(resRandom) == ' ');

		return str.substring(0,resRandom) +"A"+ str.substring(resRandom,str.length());
	}
}
