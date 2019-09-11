package com.artilekt.bank.business;

import com.artilekt.bank.BankApplication;
import com.artilekt.bank.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {Bank.class, AppConfig.class, Account.Factory.class, Account.class})
@ContextConfiguration
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class BankTest {
    @Autowired
    private Bank bank;

    private Account acc1, acc2;

    @Before
    public void setup() {
        acc1 = bank.openAccount(100, AccountType.CHECKING);
        acc2 = bank.openAccount(200, AccountType.SAVING);
    }

    @Test
    public void checkTotalTxnFee() {
        acc1.withdraw(new BigDecimal(10));
        // fee = 10 * 0.01 = 0.1
        acc1.deposit(new BigDecimal(100));
        acc2.withdraw(new BigDecimal(100));
        // fee = 0.1 + 100*0.01 = 1.1
        acc2.deposit((new BigDecimal(10)));

        assertThat(bank.getTxnFees()).isEqualTo(new BigDecimal("1.10"));
    }


    @Configuration
    @ComponentScan(basePackageClasses = BankApplication.class)
    public static class SpringConfig {

    }
}
