package com.revature.model;

public class AccountsInfo {
	
	private int accountId;
	private String accountName;
	private String customerEmail;
	private String balance;
	private String status;
	
	
	@Override
	public String toString() {
		return "[ID:" + accountId + " ACCOUNT NAME:" + accountName + " CUSTOMER EMAIL:"
				+ customerEmail + " BALANCE:" + balance + " STATUS:" + status + "]";
	}


	public AccountsInfo() {
		super();
	}


	public AccountsInfo(int accountId, String accountName, String customerEmail, String balance, String status) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.customerEmail = customerEmail;
		this.balance = balance;
		this.status = status;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((customerEmail == null) ? 0 : customerEmail.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountsInfo other = (AccountsInfo) obj;
		if (accountId != other.accountId)
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (customerEmail == null) {
			if (other.customerEmail != null)
				return false;
		} else if (!customerEmail.equals(other.customerEmail))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}


}