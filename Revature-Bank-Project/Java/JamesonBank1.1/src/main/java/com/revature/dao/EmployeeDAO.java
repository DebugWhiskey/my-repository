package com.revature.dao;

import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.AccountsInfo;
import com.revature.model.Employee;
import com.revature.model.Transactions;

public interface EmployeeDAO {

	public List<Employee> getALLEmployees();

	public Employee getEmployeeByIdAndPassword(String idIn, String passwordIn) throws UserNotFoundException;
	
	public List<Transactions> getALLTransactions() throws UserNotFoundException;
	
	public List<AccountsInfo> getPendingAccounts(String id) throws UserNotFoundException;
	
	public List<AccountsInfo> getAccountById(int id) throws UserNotFoundException;
	
	public int updateAccountStatus(int accountId, String status)throws UserNotFoundException ;
}
