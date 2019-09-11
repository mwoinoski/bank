package com.artilekt.bank.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

public class AccountTest {

	@Test
	public void depositPositiveAmount() {
		Account a = new Account();
		a.deposit(new BigDecimal("10"));
		assertEquals(a.getBalance(), new BigDecimal("10.00"));
	}
}
