package com.revature.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.services.CustomerChoices;
import com.revature.util.Keyboard;


public class CustomerMenus {
	
	
	
	private static Logger log = Logger.getLogger(CustomerMenus.class);
	
	public void displayCustomerMenu(int opts[], String mChoice[]) {
		// Display Menu
		System.out.println("========================================================");
		System.out.println("CUSTOMER MENU");
		System.out.println();
		for (int i = 0; i < opts.length; i++) {
			System.out.printf("%d. %s\n", opts[i], mChoice[i]);
		}
		System.out.println("==============");
	}

	public void customerMenu(String email) {
		
		CustomerChoices cc = new CustomerChoices();
		

		// User choice
		int choice;
		Keyboard key = new Keyboard();
		// Menu data
		int options[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		String menuChoice[] = { "View Accounts ", "Add New Account", "Make a Deposit ", "Make a Withdrawal ",
				"Make a Transfer", "Send Money ", "Receive Money", "Log out ", "EXIT" };
		// Exit variable
		int EXIT = options[options.length - 1];

		// Display Menu Call
		displayCustomerMenu(options, menuChoice);

		// Get choice from user
		choice = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, EXIT);

		// Menu loop
		while (choice != EXIT) {

			// Check choice value
			if (choice == options[0]) {
				System.out.println(menuChoice[0]);
				cc.accountOverview(email);
			} else if (choice == options[1]) {
				System.out.println(menuChoice[1]);
				cc.addNewAccount(email);
			} else if (choice == options[2]) {
				System.out.println(menuChoice[2]);
				cc.deposit(email);
			} else if (choice == options[3]) {
				System.out.println(menuChoice[3]);
				cc.withdrawal(email);
			} else if (choice == options[4]) {
				System.out.println(menuChoice[4]);
				cc.transfer(email);
			} else if (choice == options[5]) {
				System.out.println(menuChoice[5]);
				cc.sendMoney(email);
			} else if (choice == options[6]) {
				System.out.println(menuChoice[6]);
				cc.receiveMoney(email);
			} else if (choice == options[7]) {
				System.out.println(menuChoice[7]);
				customerlogOut(email);
			}
			displayCustomerMenu(options, menuChoice);
			choice = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, EXIT);

		}

		System.out.println("Thank you for using J&J Bank, have a nice day!");
		System.exit(EXIT);
	}

	private void customerlogOut(String email) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		System.out.println("Are you sure you wish to logout and return to the Welcome Menu?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int answer = scan.nextInt();

		if (answer == 1) {
			log.info(email + " has succeffully logged out.");
			WelcomeMenu.welcomeMenu();
		} else if (answer == 2) {
			customerMenu(email);
		} else {
			System.out.println("Please Input a valid response.");
			customerlogOut(email);
		}
		// date.valueof(localDate.new()) = grabbing the current date for sql

	}
}
