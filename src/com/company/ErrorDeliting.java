package com.company;

import java.util.Random;

public class ErrorDeliting implements Error {
	public String make(String str){
		int resRandom;
		do {
			resRandom = StringRandomPositionFinder.find(str);
		} while (str.charAt(resRandom) == ' ');

		return str.substring(0,resRandom) + str.substring((resRandom+1),str.length());

	}
}
