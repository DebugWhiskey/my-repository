package com.revature.services;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.exceptions.UserNotFoundException;
import com.revature.ui.CustomerMenus;
import com.revature.ui.EmployeeMenus;

public class Login {

	private static Logger log = Logger.getLogger(Login.class);

	public UserDAO userDAO;
	public EmployeeDAO employeeDAO;
	public Login lg;

	public Login() {
		this.userDAO = new UserDAOImpl();
		this.employeeDAO = new EmployeeDAOImpl();
	}

	

	public void customerLogin() {
		CustomerMenus cm = new CustomerMenus();

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.printf("Please input email: ");
		String emailIn = scan.next();
		System.out.printf("Please enter your password: ");
		String passwordIn = scan.next();

		// check username and password!
		try {
			userDAO.getUserByEmailAndPassword(emailIn, passwordIn);
			log.info(emailIn + " succeffully signed in.");
			cm.customerMenu(emailIn);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info(emailIn + "failed login.");

		}

	}

	public void employeeLogin() {
		EmployeeMenus em = new EmployeeMenus();

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.printf("Please input employee id: ");
		String idIn = scan.next();
		System.out.printf("Please enter your password: ");
		String passwordIn = scan.next();

		// check username and password!
		try {
			employeeDAO.getEmployeeByIdAndPassword(idIn, passwordIn);
			log.info(idIn + " succeffully signed in.");
			em.employeeMenu(idIn);
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
			log.info("The id: " + idIn + " was not found.");
		}

	}

}
