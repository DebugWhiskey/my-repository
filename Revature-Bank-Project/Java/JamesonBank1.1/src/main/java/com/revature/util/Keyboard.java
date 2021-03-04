package com.revature.util;

import java.util.Scanner;

public class Keyboard {
	private static Scanner in;

	public Keyboard() {
		in = new Scanner(System.in);
	}

	public int readInteger(String promptMsg, String errorMsg) {
		int num = 0;
		String strInput;
		boolean valid = false;

		// Keep looking until valid input
		while (valid == false) {
			// Prompt
			System.out.println(promptMsg);
			// grab input from keybaord
			strInput = in.nextLine();
			// try to convert String to int
			try {
				num = Integer.parseInt(strInput);
				valid = true;

			} catch (NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;

	}

	public int readInteger(String promptMsg, String errorMsg, int low, int high) {
		int num = 0;
		String strInput;
		boolean valid = false;

		// Keep looking until valid input
		while (valid == false) {
			// Prompt
			System.out.println(promptMsg);
			// grab input from keybaord
			strInput = in.nextLine();
			// try to convert String to int
			try {
				num = Integer.parseInt(strInput);
				if (num >= low && num <= high) {
					valid = true;
				}else 
					System.out.println(errorMsg);
			

			} catch (NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;

	}

	public static double readDouble(String promptMsg, String errorMsg) {
		double num = 0;
		String strInput;
		boolean valid = false;

		// Keep looking until valid input
		while (valid == false) {
			// Prompt
			System.out.println(promptMsg);
			// grab input from keybaord
			strInput = in.nextLine();
			// try to convert String to int
			try {
				num = Double.parseDouble(strInput);
				valid = true;

			} catch (NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}

}
