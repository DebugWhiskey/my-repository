package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.exceptions.PasswordInvalid;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Accounts;
import com.revature.model.Customer;
import com.revature.model.Transactions;
import com.revature.ui.CustomerMenus;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {

	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	public UserDAO userDAO;

	public UserDAOImpl() {

	}

	public UserDAOImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public List<Customer> getALLUsers() {

		List<Customer> customers = new ArrayList<>();
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// obtain a statem=ent object
			Statement stmt = con.createStatement();

			// execute query
			String sql = "SELECT * FROM jameson_bank.customers";
			ResultSet rs = stmt.executeQuery(sql);

			// process the results
			while (rs.next()) {

				// index use
//				String email = rs.getString(1);
//				String firstname = rs.getString(2);
//				String lastname = rs.getString(3);
//				String customerPassword = rs.getString(4);

				String email = rs.getString("email");
				String customerPassword = rs.getString("customer_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				Customer customer = new Customer(email, customerPassword, firstName, lastName);
				customers.add(customer);
			}
			for (Customer str : customers) {
				System.out.println(str);
			}
		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());

		}

		return customers;
	}

	@Override
	public List<Accounts> getCustomerAccounts(String email, String status) {

		List<Accounts> accounts = new ArrayList<>();
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// execute query
			String sql = "SELECT account_name, balance FROM jameson_bank.accounts WHERE customer_email = ? AND status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, status);
			ResultSet rs = pstmt.executeQuery();
			// process the results
			while (rs.next()) {

				String accountName = rs.getString("account_name");
				String balance = rs.getString("balance");

				Accounts account = new Accounts(accountName, balance);
				accounts.add(account);

			}
			for (Accounts str : accounts) {
				System.out.println(str);
			}

		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());

		}

		return accounts;
	}

	@Override
	public Customer getUserByEmail(String email) throws UserNotFoundException {

		Customer customer = null;

		try (Connection con = ConnectionUtil.getConnection()) {

			// Prepared Statment
			String sql = "SELECT * FROM jameson_bank.customers WHERE email = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String email2 = rs.getString("email");
				String customerPassword = rs.getString("customer_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				customer = new Customer(email2, customerPassword, firstName, lastName);

			} else {
				throw new UserNotFoundException("The email '" + email + "' was not found!");
			}

		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());
		}
		return customer;
	}

	@Override
	public Customer getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {

		Customer password2 = null;

		try (Connection con = ConnectionUtil.getConnection()) {

			// Prepared Statment
			String sql = "SELECT * FROM jameson_bank.customers WHERE email  = ? AND customer_password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String email2 = rs.getString("email");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String customerPassword = rs.getString("customer_password");

				password2 = new Customer(email2, firstName, lastName, customerPassword);

			} else {
				throw new UserNotFoundException("Invalid Login.");
			}

		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());
			log.info("Failed to locate the email and password combination: " + email + password);
		}
		return password2;
	}

	@Override
	public Customer getUserByPassword(String password) throws PasswordInvalid {

		Customer password2 = null;

		try (Connection con = ConnectionUtil.getConnection()) {

			// Prepared Statment
			String sql = "SELECT * FROM jameson_bank.customers WHERE customer_password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String email2 = rs.getString("email");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String customerPassword = rs.getString("customer_password");

				password2 = new Customer(email2, firstName, lastName, customerPassword);

			} else {
				throw new PasswordInvalid("Invalid Password.");
			}

		} catch (SQLException e) {
			System.out.println("An error occured with JDBC: " + e.getMessage());
		}
		return password2;
	}

	@Override
	public int insertUser(Customer customer) {
		int updateCount = 0;

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO jameson_bank.customers (email, customer_password, first_name, last_name) VALUES (?, ?, ?, ?); "
					+ "INSERT INTO jameson_bank.accounts (account_name, customer_email, balance, status) VALUES ('Primary', ?, 0.00, 'approved')";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, customer.getEmail());
			pstmt.setString(2, customer.getCustomerPassword());
			pstmt.setString(3, customer.getFirstName());
			pstmt.setString(4, customer.getLastName());
			pstmt.setString(5, customer.getEmail());

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateCount;
	}

	@Override
	public int insertNewAccount(String accountName, String email, double balance) {
		int updateCount = 0;

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO jameson_bank.accounts (account_name, customer_email, balance, status) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, accountName);
			pstmt.setString(2, email);
			pstmt.setDouble(3, balance);

			// to change with the employee discretion
			pstmt.setString(4, "Pending");

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateCount;
	}

	@Override
	public int insertMoney(String accountName, String email, double newAmount) {
		int updateCount = 0;

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "UPDATE jameson_bank.accounts SET balance = ? WHERE customer_email = ? AND account_name = ? AND status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(3, accountName);
			pstmt.setString(2, email);
			pstmt.setDouble(1, newAmount);
			pstmt.setString(4, "Approved");

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateCount;
	}

	@Override
	public double getAccountBalance(String email, String accountName) throws UserNotFoundException {

		double balance = 0;
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// execute query
			String sql = "SELECT account_name, balance FROM jameson_bank.accounts WHERE customer_email = ? AND account_name = ? AND status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, accountName);
			pstmt.setString(3, "Approved");
			ResultSet rs = pstmt.executeQuery();
			// process the results
			if (rs.next()) {

				rs.getString("account_name");
				double balance1 = rs.getDouble("balance");

				balance = balance1;

			} else {
				throw new UserNotFoundException("Invalid Account Name.");

			}
		} catch (SQLException e) {
			log.error("Failure to deposit new money @ getAccountBalance(). " + e.getMessage());

		}

		return balance;
	}

	@Override
	public int insertTransaction(String fromCustomerEmail, String toCustomerEmail, String actionType, double amount,
			String status) {
		int updateCount = 0;
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO jameson_bank.transactions (from_customer_email, to_customer_email, action_type, amount, transaction_date, status) VALUES (?, ?, ?, ?, ?, ?) ";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, fromCustomerEmail);
			pstmt.setString(2, toCustomerEmail);
			pstmt.setString(3, actionType);
			pstmt.setDouble(4, amount);
			pstmt.setDate(5, date);
			pstmt.setString(6, status);

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateCount;
	}

	@Override
	public List<Transactions> getTransactions(String email) throws UserNotFoundException {
		CustomerMenus cm = new CustomerMenus();

		List<Transactions> transaction = new ArrayList<>();
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// execute query
			String sql = "SELECT * FROM jameson_bank.transactions WHERE to_customer_email = ? AND status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, "Pending");
			ResultSet rs = pstmt.executeQuery();
			// process the results

			if (rs.next()) {
				while (rs.next()) {
					int transactionId = rs.getInt("id");
					String actionType = rs.getString("action_type");
					String fromCustomerEmail = rs.getString("from_customer_email");
					String toCustomerEmail = rs.getString("to_customer_email");
					double transferAmount = rs.getDouble("amount");
					String transactionDate = rs.getString("transaction_date");
					String status = rs.getString("status");

					Transactions transactions = new Transactions(transactionId, fromCustomerEmail, toCustomerEmail,
							transferAmount, transactionDate, actionType, status);
					transaction.add(transactions);
				}
				for (Transactions str : transaction) {
					System.out.println(str);
				} 
			}else {
				System.out.println("You have no incoming money waiting for you.");
				cm.customerMenu(email);
			}

		} catch (SQLException e) {
			log.error("The system failed to connect to the database @ getTransactions(). Error message: "
					+ e.getMessage());

		}

		return transaction;
	}

	@Override
	public double getTransactionAmount(String email, int id) throws UserNotFoundException {

		double amount = 0;
		// try-with-resources
		try (Connection con = ConnectionUtil.getConnection()) {
			// get connection

			// execute query
			String sql = "SELECT id, amount, status FROM jameson_bank.transactions WHERE to_customer_email = ? AND id = ? AND status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setInt(2, id);
			pstmt.setString(3, "Pending");
			ResultSet rs = pstmt.executeQuery();
			// process the results
			if (rs.next()) {

				rs.getInt("id");
				rs.getString(3);
				double transferAmount = rs.getDouble("amount");

				amount = transferAmount;

			} else {
				throw new UserNotFoundException("Invalid Transaction Id.");

			}
		} catch (SQLException e) {
			System.out.println("An error occured with JDBC @ getTransactionAmount().  " + e.getMessage());

		}

		return amount;
	}

	@Override
	public int updateTransaction(int accountId) {
		int updateCount = 0;

		try (Connection con = ConnectionUtil.getConnection()) {
			String sql = "UPDATE jameson_bank.transactions SET status = ? WHERE id = ? AND status = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "Recieved");
			pstmt.setInt(2, accountId);
			pstmt.setString(3, "Pending");

			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			log.error("Failed to updateTransaction(). " + e.getMessage());
		}

		return updateCount;
	}

}
