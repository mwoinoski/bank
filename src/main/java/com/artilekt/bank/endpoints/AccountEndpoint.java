package com.artilekt.bank.endpoints;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.artilekt.bank.business.Account;
import com.artilekt.bank.business.Bank;
import com.artilekt.bank.business.CRM;
import com.artilekt.bank.dto.AccountCreationRequest;

@RestController
@RequestMapping("/accounts")
public class AccountEndpoint {
	@Autowired
	private Bank bank;
	
	@Autowired
	private CRM crm;
	
	@PostMapping
	public Account openAccount(@RequestBody AccountCreationRequest req) {
		Account acc = bank.openAccount(req.getInitialBalance(), req.getAccountType());
		acc.setOwner(crm.findClientById(req.getClientId()));

		return acc;
	}
	
	
	@GetMapping
	public List<Account> listAccounts() {
		return bank.listAllAccounts();
	}
	
	@GetMapping(value="/{accountNumber}")
	public Account getAccount(@PathVariable(name = "accountNumber", required = true) String accountNumber) {
		return bank.findAccountByNumber(accountNumber);
	}
	
	@GetMapping(params = "clientId")
	public List<Account> findAccountsByClientId(@RequestParam(name = "clientId", required = true) String clientId) { return bank.findAccountsByClientId(clientId); }
	
	@GetMapping(params = {"balanceFrom", "balanceTo"})
	public List<Account> findAccountsByBalanceFromTo(@RequestParam(name = "balanceFrom", required = true) BigDecimal balanceFrom,
											   @RequestParam(name = "balanceTo",  required = true) BigDecimal balanceTo) {
		return bank.selectAccounts(Bank.MID_RANGE_BALANCE_SELECTOR.apply(balanceFrom, balanceTo));
	}
	
	
	@GetMapping(params = "balanceFrom")
	public List<Account> findAccountsByBalanceFrom(@RequestParam(name = "balanceFrom", required = true) BigDecimal balanceFrom) {
		return bank.selectAccounts(Bank.UPPER_RANGE_BALANCE_SELECTOR.apply(balanceFrom));
	}
	
	
	@GetMapping(params = "balanceTo")
	public List<Account> findAccountsByBalanceTo(@RequestParam(name = "balanceTo", required = true) BigDecimal balanceTo) {
		return bank.selectAccounts(Bank.LOWER_RANGE_BALANCE_SELECTOR.apply(balanceTo));
	}
	
}
