package com.artilekt.bank.config;

import com.artilekt.bank.business.Account;
import com.artilekt.bank.business.AccountNumberGenerator;
import com.artilekt.bank.business.AccountType;
import com.artilekt.bank.business.FeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Configuration
public class AppConfig {
    @Autowired
    private ApplicationContext ctx;


    @Bean
    public FeeCalculator zeroFeeCalculator() { return FeeCalculator.ZERO_FEE_CALCULATOR; }

    @Bean
    public FeeCalculator defaultFeeCalculator() {
        // Add curried functions for selector by activity (active accounts for the last [timeframe])
        BiFunction<BigDecimal, Account, BigDecimal> DEFAULT_WITHDRAWL_FEE = (amount, account) -> amount.multiply(new BigDecimal("0.01"));
        BiFunction<BigDecimal, Account, BigDecimal> DEFAULT_DEPOSIT_FEE   = FeeCalculator.ZERO_FEE;

        return FeeCalculator.create(DEFAULT_WITHDRAWL_FEE, DEFAULT_DEPOSIT_FEE);
    }

    @Bean
    public FeeCalculator feeCalculator() { return defaultFeeCalculator(); }

    @Bean
    public AccountNumberGenerator accountNumberGenerator() { return AccountNumberGenerator.DEFAULT; }


    @Bean
    public Account.Creator accountCreator() {
        return new Account.Creator() {
            @Override
            @Bean
            public Account createAccount(BigDecimal balance, AccountType accountType) {
                return ctx.getBean(Account.class, accountNumberGenerator().generateAccountNumber(), balance, accountType);
            }
        };
    }

}
