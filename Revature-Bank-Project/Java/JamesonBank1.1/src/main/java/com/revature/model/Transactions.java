package com.revature.model;

public class Transactions {
	
	int transactionId;
	String fromCustomerEmail;
	String toCustomerEmail;
	double amount;
	String transactiondate;
	String actionType;
	String status;
	
	
	



	@Override
	public String toString() {
		return "TRANSACTION ID: [ " + transactionId + " ] FROM: [ " + fromCustomerEmail
				+ " ] TO: [ " + toCustomerEmail + " ] AMOUNT: [ $" + amount + " ] ON: [ " + transactiondate
				+ " ] TYPE: [ " + actionType + " ] STATUS: [" + status + "]";
	}






	public Transactions() {
		super();
	}






	public Transactions(int transactionId, String fromCustomerEmail, String toCustomerEmail, double amount,
			String transactiondate, String actionType, String status) {
		super();
		this.transactionId = transactionId;
		this.fromCustomerEmail = fromCustomerEmail;
		this.toCustomerEmail = toCustomerEmail;
		this.amount = amount;
		this.transactiondate = transactiondate;
		this.actionType = actionType;
		this.status = status;
	}






	public int getTransactionId() {
		return transactionId;
	}






	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}






	public String getFromCustomerEmail() {
		return fromCustomerEmail;
	}






	public void setFromCustomerEmail(String fromCustomerEmail) {
		this.fromCustomerEmail = fromCustomerEmail;
	}






	public String getToCustomerEmail() {
		return toCustomerEmail;
	}






	public void setToCustomerEmail(String toCustomerEmail) {
		this.toCustomerEmail = toCustomerEmail;
	}






	public double getAmount() {
		return amount;
	}






	public void setAmount(double amount) {
		this.amount = amount;
	}






	public String getTransactiondate() {
		return transactiondate;
	}






	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate;
	}






	public String getActionType() {
		return actionType;
	}






	public void setActionType(String actionType) {
		this.actionType = actionType;
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
		result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fromCustomerEmail == null) ? 0 : fromCustomerEmail.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toCustomerEmail == null) ? 0 : toCustomerEmail.hashCode());
		result = prime * result + transactionId;
		result = prime * result + ((transactiondate == null) ? 0 : transactiondate.hashCode());
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
		Transactions other = (Transactions) obj;
		if (actionType == null) {
			if (other.actionType != null)
				return false;
		} else if (!actionType.equals(other.actionType))
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (fromCustomerEmail == null) {
			if (other.fromCustomerEmail != null)
				return false;
		} else if (!fromCustomerEmail.equals(other.fromCustomerEmail))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (toCustomerEmail == null) {
			if (other.toCustomerEmail != null)
				return false;
		} else if (!toCustomerEmail.equals(other.toCustomerEmail))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (transactiondate == null) {
			if (other.transactiondate != null)
				return false;
		} else if (!transactiondate.equals(other.transactiondate))
			return false;
		return true;
	}

	




	}