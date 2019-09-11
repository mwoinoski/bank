package com.artilekt.bank.business;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public interface FeeCalculator {
	public BigDecimal calculateWithdrawlFee(BigDecimal amount, Account account);
	public BigDecimal calculateDepositFee(BigDecimal amount, Account account);
	
	public final static BiFunction<BigDecimal, Account, BigDecimal> ZERO_FEE = (amount, account) -> BigDecimal.ZERO;

	public final static FeeCalculator ZERO_FEE_CALCULATOR = create(ZERO_FEE, ZERO_FEE);	
	
	public static FeeCalculator create(BiFunction<BigDecimal, Account, BigDecimal> withdrawlFeeCalcAlgo,
									   BiFunction<BigDecimal, Account, BigDecimal> depositFeeCalcAlgo) {
		return new FeeCalculator() {
			@Override
			public BigDecimal calculateWithdrawlFee(BigDecimal amount, Account account) {
				return withdrawlFeeCalcAlgo.apply(amount, account);
			}

			@Override
			public BigDecimal calculateDepositFee(BigDecimal amount, Account account) {
				return depositFeeCalcAlgo.apply(amount, account);
			}
		};
	}
	
/*

	public static class Manager implements FeeCalculator {		
		private BiFunction<BigDecimal, Account, BigDecimal> withdrawlFeeCalcAlgo = ZERO_FEE;
		private BiFunction<BigDecimal, Account, BigDecimal> depositFeeCalcAlgo   = ZERO_FEE;
				
		public Manager() {}

		public Manager(BiFunction<BigDecimal, Account, BigDecimal> withdrawlFeeCalcAlgo,
				BiFunction<BigDecimal, Account, BigDecimal> depositFeeCalcAlgo) {
			this.withdrawlFeeCalcAlgo = withdrawlFeeCalcAlgo;
			this.depositFeeCalcAlgo = depositFeeCalcAlgo;
		}

		@Override
		public BigDecimal calculateWithdrawlFee(BigDecimal amount, Account account) { return withdrawlFeeCalcAlgo.apply(amount, account); }
		
		@Override
		public BigDecimal calculateDepositFee(BigDecimal amount, Account account) {	return depositFeeCalcAlgo.apply(amount, account); }

		public void setWithdrawlFeeCalcAlgo(BiFunction<BigDecimal, Account, BigDecimal> withdrawlFeeCalcAlgo) {
			this.withdrawlFeeCalcAlgo = withdrawlFeeCalcAlgo;
		}

		public void setDepositFeeCalcAlgo(BiFunction<BigDecimal, Account, BigDecimal> depositFeeCalcAlgo) {
			this.depositFeeCalcAlgo = depositFeeCalcAlgo;
		}		
	}
	
*/


	
}
