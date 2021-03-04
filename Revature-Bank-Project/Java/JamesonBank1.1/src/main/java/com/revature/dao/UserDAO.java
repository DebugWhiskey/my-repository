package com.revature.dao;

import java.util.List;

import com.revature.exceptions.PasswordInvalid;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Accounts;
import com.revature.model.Customer;
import com.revature.model.Transactions;

public interface UserDAO {
	
	public List<Customer> getALLUsers();
	
	public List<Accounts> getCustomerAccounts(String email, String status);
	
	public Customer getUserByEmailAndPassword(String email, String password) throws UserNotFoundException;
	
	public Customer getUserByPassword(String password) throws PasswordInvalid;
	
	public int insertUser(Customer user);
	
	public int insertNewAccount(String accountName, String email, double balance);
	
	public int insertMoney(String accountName, String email, double newAmount);

	public double getAccountBalance(String email, String accountName) throws UserNotFoundException;

	public int insertTransaction(String fromCustomerEmail, String toCustomerEmail,
			 String actionType, double amount, String status);

	public List<Transactions> getTransactions(String email) throws UserNotFoundException;

	double getTransactionAmount(String email, int id) throws UserNotFoundException;
	
	public int updateTransaction(int accountId);

	public Customer getUserByEmail(String email) throws UserNotFoundException;

	
	
}
