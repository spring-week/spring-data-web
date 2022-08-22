package com.revature.exceptions;

public class FoodNotFoundException extends RuntimeException{

	public FoodNotFoundException(String msg) {
		super(msg);
	}

}
