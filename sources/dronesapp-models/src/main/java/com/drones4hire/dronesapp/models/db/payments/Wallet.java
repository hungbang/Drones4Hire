package com.drones4hire.dronesapp.models.db.payments;

import java.math.BigDecimal;
import java.util.List;

import com.drones4hire.dronesapp.models.db.AbstractEntity;
import com.drones4hire.dronesapp.models.db.commons.Currency;

public class Wallet extends AbstractEntity {
	private static final long serialVersionUID = 5407593528058423401L;

	private Long userId;
	private BigDecimal balance;
	private Currency currnecy;
	private String paymentToken;
	private List<Transaction> transactions;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Currency getCurrnecy() {
		return currnecy;
	}

	public void setCurrnecy(Currency currnecy) {
		this.currnecy = currnecy;
	}

	public String getPaymentToken() {
		return paymentToken;
	}

	public void setPaymentToken(String paymentToken) {
		this.paymentToken = paymentToken;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
