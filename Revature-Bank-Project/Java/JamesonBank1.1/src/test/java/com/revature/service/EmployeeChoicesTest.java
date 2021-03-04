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
import com.revature.dao.EmployeeDAO;
import com.revature.dao.UserDAO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.AccountsInfo;
import com.revature.model.Customer;
import com.revature.model.Transactions;
import com.revature.services.EmployeeChoices;

public class EmployeeChoicesTest {

	public static EmployeeChoices ec;

	public static UserDAO userDAO;
	public static EmployeeDAO employeeDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDAO = mock(UserDAO.class);
		employeeDAO = mock(EmployeeDAO.class);
		ec = mock(EmployeeChoices.class);

		List<AccountsInfo> list2 = new ArrayList<>();
		list2.add(new AccountsInfo(1, "aaa", "bbb", "ccc", "ddd"));
		List<Transactions> list3 = new ArrayList<>();
		list3.add(new Transactions(1, "aaa", "bbb", 2, "ccc", "ddd", "eee"));
		Customer customer = new Customer("aaa", "bbb", "ccc", "ddd");
		when(userDAO.getUserByEmail("email")).thenReturn(customer);
		when(employeeDAO.getPendingAccounts("id")).thenReturn(list2);
		when(employeeDAO.getAccountById(3)).thenReturn(list2);
		when(employeeDAO.getALLTransactions()).thenReturn(list3);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		new EmployeeChoices();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCustomerAccount() throws UserNotFoundException {
		ec.getCustomerAccount();

		verify(ec, times(1)).getCustomerAccount();

	}

	@Test
	public void testPendingAccounts() throws UserNotFoundException {
		ec.pendingAccounts("id");

		verify(ec, times(1)).pendingAccounts("id");
	}

	@Test
	public void testViewTransactions() throws UserNotFoundException {
		ec.viewTransactions();

		verify(ec, times(1)).viewTransactions();
	}

}
