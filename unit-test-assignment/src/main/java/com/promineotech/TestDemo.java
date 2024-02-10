package com.promineotech;

import java.util.Random;

public class TestDemo {
	public int addPositive(int a, int b) {
		int x = 0;
		try {
			if(a > 0 && b > 0) {
				x = a+b;
			}
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Both parameters should be positive!");
		}
		return x;
	}
	public int randomNumberSquared() {
		int x = getRandomInt();
		return x * x;
	}
	int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) + 1;
	}
	public int multiplyPositive(int a, int b) {
		int x = 0;
		try {
			if (a > 0 && b > 0) {
				x = a*b;
			}
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException("Bothy parameters should be positive");
		}
		return x;
	}
}
