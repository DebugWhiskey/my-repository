package com.revature.services;

import java.util.Scanner;

import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.model.Customer;

public class SignUp {
	public static void newAccount() {
		
		UserDAO dao = new UserDAOImpl();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		String email = null;
		String password = null;
		String firstName = null;
		String lastName = null;

		System.out.printf("Please enter your email: ");
		email = scan.next();
		System.out.printf("Enter your password: ");
		password = scan.next();
		System.out.printf("First name: ");
		firstName = scan.next();
		System.out.printf("Last name: ");
		lastName = scan.next();

		Customer customer = new Customer(email, password, firstName, lastName);
		dao.insertUser(customer);
		
		// remember to remove!
		System.out.println(dao.getALLUsers());
		

		
	}

}
