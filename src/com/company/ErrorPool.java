package com.company;

import java.util.ArrayList;

public class ErrorPool {
	private int lastError = -1;
	private ArrayList<Error> errors;

	ErrorPool(ArrayList<Error> errorsToBeUsed) {
		errors = errorsToBeUsed;
	}

	public Error getNextError() {
		lastError++;
		if (lastError > errors.size() - 1) {
			lastError = 0;
		}
		return errors.get(lastError);
	}
}
