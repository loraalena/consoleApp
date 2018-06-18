package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		Scanner str = new Scanner(System.in);
		String arguments = str.nextLine();
		String[] arr = arguments.split(" ");

		String lang = arr[0];
		int number = Integer.parseInt(arr[1]);
		float err = Float.parseFloat(arr[2]);

		ErrorDeliting errorDeliting = new ErrorDeliting();
		ErrorAdding errorAdding = new ErrorAdding();
		ErrorSwapping errorSwapping = new ErrorSwapping();

		String names = readFile(lang, "name.txt");
		String surnames = readFile(lang, "surnames.txt");
		String middleNames = readFile(lang, "middleNames.txt");
		String towns = readFile(lang, "towns.txt");
		String streets = readFile(lang, "streets.txt");

		String[] splitNames = splitByLinebreaks(names);
		String[] splitSurnames = splitByLinebreaks(surnames);
		String[] splitmiddleNames = splitByLinebreaks(middleNames);
		String[] splittowns = splitByLinebreaks(towns);
		String[] splitstreets = splitByLinebreaks(streets);

		int[] terms = new int[number];
		if (err / number > 1) {
			terms = splitSum(Math.round(err), number, (int) Math.floor(err / number), (int) Math.ceil(err / number));
		} else if (err / number > 0) {
			int numberOfRecordsBeforeError = Math.round(number / err);
			int allErrorsCount = 0;
			for (int i = 0; i < number; i++) {
				if (i % numberOfRecordsBeforeError == 0) {
					terms[i] = 1;
					allErrorsCount += 1;
				} else {
					terms[i] = 0;
				}
			}
			//
			if(allErrorsCount > err){
				for(int i = terms.length-1; i > 0; i--){
					if(terms[i] == 1){
						terms[i] = 0;
						break;
					}
				}
			}
			else if(allErrorsCount < err){
				for(int i = terms.length-1; i > 0; i--){
					if(terms[i] == 0){
						terms[i] = 1;
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < number; i++) {
				terms[i] = 0;
			}
		}

		ArrayList<Error> el = new ArrayList<Error>(3);
		el.add(errorAdding);
		el.add(errorDeliting);
		el.add(errorSwapping);
		ErrorPool ep = new ErrorPool(el);

		for (int i = 0; i < number; i++) {
			String record = generateRecord(splitNames, splitSurnames, splitmiddleNames, splittowns, splitstreets);
			for (int errorIndex = 0; errorIndex < terms[i]; errorIndex++) {
				Error nextError = ep.getNextError();
				record = nextError.make(record);
			}
			System.out.println(record);
		}
	}

	static String readFile(String lang, String path) {

		byte[] encoded = new byte[0];
		try {
			encoded = Files.readAllBytes(Paths.get("./data/" + lang + "/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded, StandardCharsets.UTF_8);
	}

	public static String[] splitByLinebreaks(String name) {
		return name.split("\r\n");
	}

	public static String getRandomElementFromArray(String[] array) {
		int rnd = new Random().nextInt(array.length);
		return array[rnd];
	}

	public static String generateRecord(String[] names, String[] surnames, String[] middleNames, String[] townss, String[] streets) {

		Random r = new Random();
		int Low = 10;
		int High = 100;
		int rezRandom = r.nextInt(High - Low) + Low;

		String record = getRandomElementFromArray(names) + " " + getRandomElementFromArray(surnames) + " " + getRandomElementFromArray(middleNames) + ", "
				+ getRandomElementFromArray(townss) + ", " + getRandomElementFromArray(streets) + ", " + rezRandom + "/" + (rezRandom / 3 + rezRandom / 10);
		return record;
	}

	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public static int[] splitSum(int sum, int count, int minPrice, int maxPrice) {
		int[] prices = new int[count];

		int j = 0;
		for (int i = count - 1; i > 0; i--) {
			int diff = sum - i * maxPrice;


			if (diff > 0)
				prices[j] = getRandomNumberInRange(minPrice, maxPrice);
			else {
				int max;
				if ((sum - (i * minPrice)) > maxPrice) max = maxPrice;
				else if ((sum - (i * minPrice)) <= 0) max = sum;
				else max = sum - (i * minPrice);

				prices[j] = getRandomNumberInRange(minPrice, max);
			}

			if (i > 1)
				sum -= prices[j];

			j++;
		}
		prices[j] = sum - prices[j - 1];

		return prices;
	}
}

