package com.company;

import java.util.Random;

public class StringRandomPositionFinder {
	public static int find(String str) {
		Random r = new Random();
		int low = 1;
		int high = str.length() - 2;
		return r.nextInt(high - low) + low;
	}
}
