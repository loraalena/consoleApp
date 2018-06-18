package com.company;

import java.util.Random;

public class ErrorSwapping implements Error {
	public String make(String str) {
		int resRandom;
		do {
			resRandom = StringRandomPositionFinder.find(str);
		} while (str.charAt(resRandom) == ' ' || str.charAt(resRandom + 1) == ' ');

		return str.substring(0, resRandom) + str.substring(resRandom + 1, resRandom + 2) + str.substring(resRandom, resRandom + 1) + str.substring(resRandom + 2, str.length());
	}
}
