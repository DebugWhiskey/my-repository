package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.UserDAO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.services.Login;

public class LoginTest {

	public static Login lg;

	public static UserDAO userDAO;
	public static EmployeeDAO employeeDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDAO = mock(UserDAO.class);
		employeeDAO = mock(EmployeeDAO.class);
		lg = mock(Login.class);

		Customer customer = new Customer("aaa", "bbb", "ccc", "ddd");
		Employee employee = new Employee("aaa", "bbb", "ccc", "ddd");
		when(userDAO.getUserByEmailAndPassword("abc", "123")).thenReturn(customer);
		when(employeeDAO.getEmployeeByIdAndPassword("abc", "123")).thenReturn(employee);
		

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCustomerLogin() throws UserNotFoundException {
		lg.customerLogin();
		verify(lg, times(1)).customerLogin();

	}

//	@Test(expected = UserNotFoundException.class)
//	public void testCustomerLoginFailedUser() throws UserNotFoundException {
//		userDAO.getUserByEmailAndPassword("abcd", "123").equals(userDAO.getUserByEmailAndPassword("abc", "123"));
//		
//		
//	}

	@Test
	public void testEmployeeLogin() throws UserNotFoundException {
		lg.employeeLogin();
		verify(lg, times(1)).employeeLogin();

	}

}
