package com.revature.services;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.util.Keyboard;

public class CustomerChoices {

	private static Logger log = Logger.getLogger(CustomerChoices.class);
	
	public UserDAO userDAO; 
	public CustomerChoices() {
		this.userDAO = new UserDAOImpl();
		
	}
	
	public CustomerChoices(UserDAO userDAO) {
		this.userDAO = userDAO;
		
	}

	public void accountOverview(String email) {

		UserDAO dao = new UserDAOImpl();
		dao.getCustomerAccounts(email, "Approved");
		log.info(email + " viewed their accounts.");

	}

	public void addNewAccount(String email) {
		UserDAO dao = new UserDAOImpl();

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);

		String accountName = null;
		double balance;

		System.out.printf("Enter your new account name: ");
		accountName = scan.next();
		System.out.printf("Enter your balance amount: ");
		balance = scan.nextDouble();

		dao.insertNewAccount(accountName, email, balance);
		dao.getCustomerAccounts(email, "Approved");
		log.info(email + " added the account: " + accountName + " and deposted " + balance);

	}

	public void deposit(String email) {
		UserDAO dao = new UserDAOImpl();

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		String depositTo = null;
		double depositAmount;
		double currentAmount;
		double newAmount;
		String actionType = "Deposit";
		String status = "Approved";

		dao.getCustomerAccounts(email, "Approved");
		System.out.printf("Enter Account Name: ");
		depositTo = fetch.next();

		try {
			System.out.println(depositTo + " currently has: " + dao.getAccountBalance(email, depositTo));
		} catch (UserNotFoundException e) {
			System.out.printf(e.getMessage());
			deposit(email);
		}

		depositAmount = Keyboard.readDouble("Enter Amount: ", "Invalid entry. Try again.");

		// get and assign current account balance
		try {
			currentAmount = dao.getAccountBalance(email, depositTo);
			newAmount = currentAmount + depositAmount;
			dao.insertMoney(depositTo, email, newAmount);
			System.out.println(depositTo + " now has a balance of: " + dao.getAccountBalance(email, depositTo));
			log.info(email + " successfully deposited " + depositAmount + " into " + depositTo);
			// log trasaction
			dao.insertTransaction(email, email, actionType, depositAmount, status);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info(email + " failed to deposit " + depositAmount + " into " + depositTo);
		}

	}

	public void withdrawal(String email) {

		UserDAO dao = new UserDAOImpl();

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		String withdrawalFrom = null;
		double withdrawalAmount;
		double currentAmount;
		double newAmount;
		String actionType = "Withdrawal";
		String status = "Approved";

		dao.getCustomerAccounts(email, "Approved");
		System.out.printf("Enter Account Name: ");
		withdrawalFrom = fetch.next();

		try {
			System.out.println(dao.getAccountBalance(email, withdrawalFrom));
		} catch (UserNotFoundException e) {
			System.out.printf(e.getMessage());
			withdrawal(email);
		}

		withdrawalAmount = Keyboard.readDouble("Enter Amount: ", "Invalid entry. Try again.");

		// get and assign current account balance
		try {
			currentAmount = dao.getAccountBalance(email, withdrawalFrom);
			newAmount = currentAmount - withdrawalAmount;
			if (newAmount < 0) {
				System.out.println("You can't withdrawal more than the account balance.");
				withdrawal(email);
			}
			dao.insertMoney(withdrawalFrom, email, newAmount);
			System.out
					.println(withdrawalFrom + " now has a balance of: " + dao.getAccountBalance(email, withdrawalFrom));
			log.info(email + " successfully withdrew " + withdrawalAmount + " from " + withdrawalFrom);
			// log trasaction
			dao.insertTransaction(email, email, actionType, withdrawalAmount, status);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info(email + " failed to withdraw " + withdrawalAmount + " from " + withdrawalFrom);
		}

	}

	public void transfer(String email) {
		UserDAO dao = new UserDAOImpl();

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		String withdrawalFrom = null;
		String depositTo = null;
		double transferAmount;
		double currentSenderAmount;
		double currentReceiverAmount;
		double newSenderAmount;
		double newReceiverAmount;
		String actionType = "Transfer";
		String status = "Approved";

		dao.getCustomerAccounts(email, "Approved");
		System.out.printf("Enter Withdrawal Account Name: ");
		withdrawalFrom = fetch.next();
		// Verify the withdrawal account
		try {
			dao.getAccountBalance(email, withdrawalFrom);
		} catch (UserNotFoundException e) {
			System.out.printf(e.getMessage());
			transfer(email);
		}
		dao.getCustomerAccounts(email, "Approved");
		System.out.printf("Enter Deposit Account Name: ");
		depositTo = fetch.next();
		try {
			dao.getAccountBalance(email, depositTo);
		} catch (UserNotFoundException e) {
			System.out.printf(e.getMessage());
			transfer(email);
		}

		transferAmount = Keyboard.readDouble("Enter Transfer Amount: ", "Invalid entry. Try again.");

		// get and assign current account balance
		try {
			currentSenderAmount = dao.getAccountBalance(email, withdrawalFrom);
			newSenderAmount = currentSenderAmount - transferAmount;
			if (newSenderAmount < 0) {
				System.out.println("You can't withdrawal more than the account balance.");
				transfer(email);
			}
			dao.insertMoney(withdrawalFrom, email, newSenderAmount);
			System.out
					.println(withdrawalFrom + " now has a balance of: " + dao.getAccountBalance(email, withdrawalFrom));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());

		}
		// get and assign current account balance
		try {
			currentReceiverAmount = dao.getAccountBalance(email, depositTo);
			newReceiverAmount = currentReceiverAmount + transferAmount;
			dao.insertMoney(depositTo, email, newReceiverAmount);
			System.out.println(depositTo + " now has a balance of: " + dao.getAccountBalance(email, depositTo));
			// log trasaction
			dao.insertTransaction(email, depositTo, actionType, transferAmount, status);
			log.info(email + " successfully transferred " + transferAmount + " from " + withdrawalFrom + " into "
					+ depositTo);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info(
					email + " failed to transfer " + transferAmount + " from " + withdrawalFrom + " into " + depositTo);
		}

	}

	public void sendMoney(String email) {
		UserDAO dao = new UserDAOImpl();

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		String withdrawalFrom = null;
		String depositTo = null;
		double transferAmount;
		double currentSenderAmount;
		double newSenderAmount;
		String actionType = "Send";
		String status = "Pending";

		dao.getCustomerAccounts(email, "Approved");
		System.out.printf("Enter Withdrawal Account Name: ");
		withdrawalFrom = fetch.next();
		// Verify the withdrawal account
		try {
			System.out.println(withdrawalFrom + " has a balance of " + dao.getAccountBalance(email, withdrawalFrom));

		} catch (UserNotFoundException e) {
			System.out.printf(e.getMessage());
			sendMoney(email);
		}
		System.out.printf("Enter the email of the person you wish to send money to: ");
		depositTo = fetch.next();

		// Verify email account
		try {
			dao.getUserByEmail(depositTo);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			sendMoney(email);
		}

		transferAmount = Keyboard.readDouble("Enter Transfer Amount: ", "Invalid entry. Try again.");

		// get and assign current account balance (deposit)
		try {

			log.info(email + " successfully posted a transaction of $" + transferAmount + " to user " + depositTo);
		} catch (Exception e) {
			log.error(email + " failed to post a transaction for " + transferAmount + " to user " + depositTo + ". "
					+ e.getMessage());
		}

		// get and assign current account balance (withdrawal)
		try {
			currentSenderAmount = dao.getAccountBalance(email, withdrawalFrom);
			newSenderAmount = currentSenderAmount - transferAmount;
			if (newSenderAmount < 0) {
				System.out.println("You can't withdrawal more than the account balance.");
				sendMoney(email);
			}
			dao.insertMoney(withdrawalFrom, email, newSenderAmount);
			System.out
					.println(withdrawalFrom + " now has a balance of: " + dao.getAccountBalance(email, withdrawalFrom));
			// log transaction
			dao.insertTransaction(email, depositTo, actionType, transferAmount, status);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	public void receiveMoney(String email) {

		@SuppressWarnings("resource")
		Scanner fetch = new Scanner(System.in);

		Keyboard key = new Keyboard();

		UserDAO dao = new UserDAOImpl();

		int approval;
		String accountName;
		double balance;
		double newAmount;
		int accountId;

		try {
			dao.getTransactions(email);
		} catch (UserNotFoundException e) {
			System.out.println("You do not have any incoming money at this time.");

		}
		System.out.printf("Enter transaction ID: ");
		accountId = fetch.nextInt();

		try {
			dao.getTransactionAmount(email, accountId);
		} catch (UserNotFoundException e) {
			System.out.println("Could not find a transaction with the account id: " + accountId);

		}

		System.out.println("1. Approve");
		System.out.println("2. Deny");
		approval = key.readInteger("Enter Choice: ", "Invalid entry. Try again.", 1, 2);

		if (approval == 1) {
			try {
				dao.getCustomerAccounts(email, "Approved");
				System.out.println("Which account do you wish to deposit into? ");
				accountName = fetch.next();

				balance = dao.getAccountBalance(email, accountName);

				double transferAmount = dao.getTransactionAmount(email, accountId);

				newAmount = transferAmount + balance;

				dao.insertMoney(accountName, email, newAmount);

				// log trasaction
				dao.updateTransaction(accountId);
				System.out
						.println(accountName + " now had a balance of: $" + dao.getAccountBalance(email, accountName));

			} catch (UserNotFoundException e) {
				log.error("Failed to transfer money to your account. " + e.getMessage());
			}
		} else {
			System.out.println("Return to menu.");

		}

	}

}
