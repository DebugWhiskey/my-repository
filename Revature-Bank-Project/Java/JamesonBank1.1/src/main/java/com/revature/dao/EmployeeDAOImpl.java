package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.exceptions.UserNotFoundException;
import com.revature.model.AccountsInfo;
import com.revature.model.Employee;
import com.revature.model.Transactions;
import com.revature.ui.EmployeeMenus;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public List<Employee> getALLEmployees() {

		List<Employee> employees = new ArrayList<>();
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// obtain a statem=ent object
			Statement stmt = con.createStatement();

			// execute query
			String sql = "SELECT * FROM jameson_bank.employees";
			ResultSet rs = stmt.executeQuery(sql);

			// process the results
			while (rs.next()) {

				// index use
//				String email = rs.getString(1);
//				String firstname = rs.getString(2);
//				String lastname = rs.getString(3);
//				String customerPassword = rs.getString(4);

				String id = rs.getString("id");
				String employeePassword = rs.getString("employee_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				Employee employee = new Employee(id, employeePassword, firstName, lastName);
				employees.add(employee);

			}
		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());

		}

		return employees;

	}

	@Override
	public Employee getEmployeeByIdAndPassword(String idIn, String passwordIn) throws UserNotFoundException {

		Employee id = null;

		try (Connection con = ConnectionUtil.getConnection()) {

			// Prepared Statment
			String sql = "SELECT * FROM jameson_bank.employees WHERE id  = ? AND employee_password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, idIn);
			pstmt.setString(2, passwordIn);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String id2 = rs.getString("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String employeePassword = rs.getString("employee_password");

				id = new Employee(id2, firstName, lastName, employeePassword);

			} else {
				throw new UserNotFoundException("Invalid Login.");
			}

		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());
			log.info("Failed to find the id and password combination of: " + idIn + passwordIn);
		}
		return id;

	}

	@Override
	public List<Transactions> getALLTransactions() throws UserNotFoundException {

		List<Transactions> transaction = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM jameson_bank.transactions";
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int transactionId = rs.getInt("id");
				String fromCustomerEmail = rs.getString("from_customer_email");
				String toCustomerEmail = rs.getString("to_customer_email");
				double amount = rs.getDouble("amount");
				String transactionDate = rs.getString("transaction_date");
				String actionType = rs.getString("action_type");
				String status = rs.getString("status");

				Transactions transactions = new Transactions(transactionId, fromCustomerEmail, toCustomerEmail, amount,
						transactionDate, actionType, status);
				transaction.add(transactions);

			}
			for (Transactions str : transaction) {
				System.out.println(str);
			}

		} catch (SQLException e) {
			log.error("The system failed to connect to the database @ getALLTransactions(). Error message: "
					+ e.getMessage());

		}

		return transaction;

	}

	@Override
	public List<AccountsInfo> getPendingAccounts(String id) throws UserNotFoundException {
		EmployeeMenus em = new EmployeeMenus();

		List<AccountsInfo> accounts = new ArrayList<>();
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// execute query
			String sql = "SELECT * FROM jameson_bank.accounts WHERE status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "Pending");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// process the results
				while (rs.next()) {

					int accountId = rs.getInt("account_id");
					String accountName = rs.getString("account_name");
					String customerEmail = rs.getString("customer_email");
					String balance = rs.getString("balance");
					String status = rs.getString("status");

					AccountsInfo account = new AccountsInfo(accountId, accountName, customerEmail, balance, status);
					accounts.add(account);
				}
				for (AccountsInfo str : accounts) {
					System.out.println(str);
				}
			}else {
				System.out.println("There are no pending accounts today.");
				em.employeeMenu(id);
		}

	}catch(

	SQLException e)
	{
		log.error("The system failed to connect to the database @ getPendingAccounts(). Error message: "
				+ e.getMessage());

	}

	return accounts;
	}

	@Override
	public List<AccountsInfo> getAccountById(int id) throws UserNotFoundException {

		List<AccountsInfo> accounts = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnection()) {

			// Prepared Statment
			String sql = "SELECT * FROM jameson_bank.accounts WHERE account_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				int accountId = rs.getInt("account_id");
				String accountName = rs.getString("account_name");
				String customerEmail = rs.getString("customer_email");
				String balance = rs.getString("balance");
				String status = rs.getString("status");

				AccountsInfo account = new AccountsInfo(accountId, accountName, customerEmail, balance, status);
				accounts.add(account);
			}

			for (AccountsInfo str : accounts) {
				System.out.println(str);
			}
		} catch (SQLException e) {
			log.error("The system failed to connect to the database @ getAccountById(). E message: " + e.getMessage());
		}
		return accounts;
	}

	@Override
	public int updateAccountStatus(int accountId, String status) throws UserNotFoundException {
		int updateCount = 0;

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "UPDATE jameson_bank.accounts SET status =? WHERE account_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, status);
			pstmt.setInt(2, accountId);

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			log.error("The system failed to connect to the database @ updateAccountStatus(). E message: "
					+ e.getMessage());
		}

		return updateCount;
	}

}
