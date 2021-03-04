package com.revature.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.services.EmployeeChoices;
import com.revature.util.Keyboard;

public class EmployeeMenus {
		
		private static Logger log = Logger.getLogger(CustomerMenus.class);
		
		public  void displayEmployeeMenu(int opts[], String mChoice[]) {
			// Display Menu
			System.out.println("========================================================");
			System.out.println("EMPLOYEE MENU");
			System.out.println();
			for (int i = 0; i < opts.length; i++) {
				System.out.printf("%d. %s\n", opts[i], mChoice[i]);
			}
			System.out.println("==============");
		}

		public void employeeMenu(String id) {
			EmployeeChoices ec = new EmployeeChoices();

			// User choice
			int choice;
			Keyboard key = new Keyboard();
			// Menu data
			int options[] = { 1, 2, 3, 4, 5 };
			String menuChoice[] = { "View Customer Accounts ", "View Pending Accounts", "View Transactions ", "Log out ", "EXIT" };
			// Exit variable
			int EXIT = options[options.length - 1];

			// Display Menu Call
			displayEmployeeMenu(options, menuChoice);

			// Get choice from user
			choice = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, EXIT);

			// Menu loop
			while (choice != EXIT) {

				// Check choice value
				if (choice == options[0]) {
					
					System.out.println(menuChoice[0]);
					ec.getCustomerAccount();
					
				} else if (choice == options[1]) {
					
					System.out.println(menuChoice[1]);
					ec.pendingAccounts(id);
					
				} else if (choice == options[2]) {
					
					System.out.println(menuChoice[2]);
					ec.viewTransactions();
					
				} else if (choice == options[3]) {
					
					System.out.println(menuChoice[3]);
					employeelogOut(id);
				}
				displayEmployeeMenu(options, menuChoice);
				choice = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, EXIT);

			}

			System.out.println("Thank you for using J&J Bank, have a nice day!");
			System.exit(EXIT);
		}

		private void employeelogOut(String id) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);

			System.out.println("Are you sure you wish to logout and return to the Welcome Menu?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int answer = scan.nextInt();

			if (answer == 1) {
				log.info("Employee: " + id + " has succeffully logged out.");
				WelcomeMenu.welcomeMenu();
			} else if (answer == 2) {
				employeeMenu(id);
			} else {
				System.out.println("Please Input a valid response.");
				employeelogOut(id);
			}
			// date.valueof(localDate.new()) = grabbing the current date for sql

		}
	}



