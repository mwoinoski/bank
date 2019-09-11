package com.artilekt.bank.business;

import java.math.BigDecimal;

public interface TxnCallback {
	void amountWithdrawn(Account account, BigDecimal amount);
	void amountDeposited(Account account, BigDecimal amount);
	
	public static TxnCallback NOOP = new TxnCallback() {		
		@Override
		public void amountWithdrawn(Account account, BigDecimal amount) {
		}
		
		@Override
		public void amountDeposited(Account account, BigDecimal amount) {
		}
	};
}
