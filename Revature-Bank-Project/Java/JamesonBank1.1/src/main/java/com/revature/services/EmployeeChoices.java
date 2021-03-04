package com.revature.services;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.util.Keyboard;

public class EmployeeChoices {

	private static Logger log = Logger.getLogger(CustomerChoices.class);
	public UserDAO userDAO;
	public EmployeeDAO employeeDAO;

	public EmployeeChoices() {
		this.userDAO = new UserDAOImpl();
		this.employeeDAO = new EmployeeDAOImpl();
	}

	// to pass in fake DAO
	public EmployeeChoices(UserDAO userDAO, EmployeeDAO employeeDAO) {
		this.userDAO = userDAO;
		this.employeeDAO = employeeDAO;
	}

	public void getCustomerAccount() {

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		String customerEmail = null;

		System.out.printf("Please enter the customer email: ");
		customerEmail = fetch.next();

		try {
			System.out.println("Displaying all account information for: ");
			System.out.println(userDAO.getUserByEmail(customerEmail));
		} catch (UserNotFoundException e) {
			log.info("Employee failed to find account: " + customerEmail + " E messege: " + e.getMessage());
		}

	}

	public void pendingAccounts(String id) {

		Keyboard key = new Keyboard();

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		int accountId;
		String approval;
		int choice;

		try {
			System.out.println("PENDING ACCOUNTS");
			employeeDAO.getPendingAccounts(id);
		} catch (UserNotFoundException e) {
			log.info("Couldn't find getALLTransactions() E messege: " + e.getMessage());

		}

		System.out.println("==============");
		System.out.println("Enter the ID of the account you wish to approve/deny:");
		accountId = fetch.nextInt();

		try {
			employeeDAO.getAccountById(accountId);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info("Could not find desired account. E message: " + e.getMessage());
		}

		System.out.println("1. Approve");
		System.out.println("2. Deny");

		choice = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, 2);

		if (choice == 1) {
			approval = "Approved";
		} else {
			approval = "Denied";
		}

		try {
			employeeDAO.updateAccountStatus(accountId, approval);
			System.out.println("Account Id: " + accountId + " has been successfully " + approval + ".");
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info("Could not make change. E message: " + e.getMessage());
		}

	}

	public void viewTransactions() {

		try {
			System.out.println("TRANSACTIONS");
			employeeDAO.getALLTransactions();
		} catch (UserNotFoundException e) {
			log.info("Couldn't find getALLTransactions() E messege: " + e.getMessage());

		}

	}
}
