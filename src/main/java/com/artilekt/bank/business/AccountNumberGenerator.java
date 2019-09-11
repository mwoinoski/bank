package com.artilekt.bank.business;

import java.util.UUID;

public interface AccountNumberGenerator {
	public String generateAccountNumber();
	
	public final static AccountNumberGenerator DEFAULT = new AccountNumberGenerator() {
		public final static int ACC_NUMBER_LENGTH = 8;
		@Override
		public String generateAccountNumber() {
			return UUID.randomUUID().toString().replace("-", "").substring(0, ACC_NUMBER_LENGTH);
		}
	};
}
