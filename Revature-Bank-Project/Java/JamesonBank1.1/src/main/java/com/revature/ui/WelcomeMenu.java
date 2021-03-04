package com.revature.ui;

import com.revature.services.Login;
import com.revature.services.SignUp;
import com.revature.util.Keyboard;

public class WelcomeMenu {
	public static void displayMenu(int opts[], String mChoice[]) {
		// Display Menu
		System.out.println("========================================================");
		System.out.println(" Welcome to Jameson & Jameson Bank! What would you like to do?");
		System.out.println();
		for (int i = 0; i < opts.length; i++) {
			System.out.printf("%d. %s\n", opts[i], mChoice[i]);
		}
		System.out.println("==============");
		
	}

	public static void welcomeMenu() {
		Login login = new Login();
		
		Keyboard key = new Keyboard();
		// User choice
		int choice;
		// Menu data
		int options[] = { 1, 2, 3, 4 };
		String menuChoice[] = { "Customer Login ", "Sign up for a J&J Bank Customer Account!", "Employee Login",
				"EXIT" };
		// Exit variable
		int EXIT = options[options.length - 1];

		// Display Menu Call
		displayMenu(options, menuChoice);

		// Get choise from user
		choice = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, EXIT);

		// Menu loop
		while (choice != EXIT) {

			// Check choice value
			if (choice == options[0]) {
				System.out.println(menuChoice[0]);
				login.customerLogin();

			} else if (choice == options[1]) {
				System.out.println(menuChoice[1]);
				System.out.println("--> NEW ACCOUNT");
				SignUp.newAccount();
				welcomeMenu();

			} else if (choice == options[2]) {
				System.out.println(menuChoice[2]);
				login.employeeLogin();

			}

		}
		System.out.println("Thank you for using J&J Bank, have a nice day!");

		System.exit(EXIT);
	}

}
