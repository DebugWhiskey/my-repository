package com.revature.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.UserDAO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Accounts;
import com.revature.model.Customer;
import com.revature.services.CustomerChoices;

public final class CustomerChoicesTest {

	public static CustomerChoices cc;

	@Mock
	public static UserDAO userDAO;
	public static EmployeeDAO employeeDAO;

	@BeforeClass

	public static void setUpBeforeClass() throws Exception {
		userDAO = mock(UserDAO.class);
		employeeDAO = mock(EmployeeDAO.class);
		cc = mock(CustomerChoices.class);

		List<Accounts> list = new ArrayList<>();
		list.add(new Accounts("aaa", "bbb"));
		Customer customer = new Customer();
		when(userDAO.getUserByEmailAndPassword("abc", "123")).thenReturn(customer);
		when(userDAO.getAccountBalance("abc", "1234")).thenReturn((double) 1234);
		when(userDAO.insertMoney("aaa", "bbb", 2)).thenReturn(6);
		when(userDAO.insertTransaction("ddd", "ccc", "eee", 3, "fff")).thenReturn(6);
		when(userDAO.getCustomerAccounts("email", "status")).thenReturn(list);
		when(userDAO.getUserByEmail("email")).thenReturn(customer);
		

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
	public void testDeposit() throws UserNotFoundException {
		cc.deposit("email");

		verify(cc, times(1)).deposit("email");
	}

	@Test
	public void testWithdrawal() throws UserNotFoundException {
		cc.withdrawal("email");

		verify(cc, times(1)).withdrawal("email");
	}
	@Test
	public void testTransfer() throws UserNotFoundException {
		cc.transfer("email");
		
		verify(cc, times(1)).transfer("email");
	}
	@Test
	public void testSendMoney() throws UserNotFoundException {
		cc.sendMoney("email");
		
		verify(cc, times(1)).sendMoney("email");
	}
	@Test
	public void testReceiveMoney() throws UserNotFoundException {
		cc.receiveMoney("email");
		
		verify(cc, times(1)).receiveMoney("email");
	}

}
