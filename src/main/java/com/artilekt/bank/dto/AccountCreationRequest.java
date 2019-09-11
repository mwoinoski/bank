package com.artilekt.bank.dto;

import java.math.BigDecimal;

import com.artilekt.bank.business.AccountType;

public class AccountCreationRequest {
	
	private BigDecimal initialBalance;
	private AccountType accountType;
	private String clientId;

	public BigDecimal getInitialBalance() {
		return initialBalance;
	}

	public AccountType getAccountType() {
		return accountType;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	

}
