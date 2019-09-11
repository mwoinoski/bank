package com.artilekt.bank.business;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;

import com.artilekt.bank.BankApplication;
import org.junit.Test;

import com.artilekt.bank.business.Account;
import com.artilekt.bank.business.Bank;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {Bank.class, AppConfig.class, Account.Factory.class, Account.class})
//@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BankApplication.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class BankPlayground {
	@Autowired
	private Bank bank;

	@Test
	public void printHighValueAccounts() {
		bank.openAccount(100);
		bank.openAccount(15);
		bank.openAccount(140);
		bank.openAccount(175);
		bank.openAccount(45);

		assertEquals(5, bank.getTotalNumberOfAccounts());
		bank.selectAccounts(Bank.UPPER_RANGE_BALANCE_SELECTOR.apply(new BigDecimal(100)))
			.forEach(System.out::println);
	}
	

	@Test
	public void printTxnFees() {
		Account a1 = bank.openAccount(100);
		Account a2 = bank.openAccount(15);

		assertEquals(2, bank.getTotalNumberOfAccounts());

		a1.withdraw(new BigDecimal(10));
		a1.withdraw(new BigDecimal(20));
		a2.deposit(new BigDecimal(100));
		a2.deposit(new BigDecimal(100));
		a2.withdraw(new BigDecimal(30));
				
		System.out.println(bank.getTxnFees());
	}	
}
