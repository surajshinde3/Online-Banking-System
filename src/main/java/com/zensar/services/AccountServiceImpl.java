package com.zensar.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.model.SalaryAccount;
import com.zensar.model.SalaryTransaction;
import com.zensar.model.SavingsAccount;
import com.zensar.model.SavingsTransaction;
import com.zensar.model.User;
import com.zensar.repository.SalaryRepository;
import com.zensar.repository.SavingsRepository;
import com.zensar.repository.UserRepository;

@Service
public class AccountServiceImpl implements AccountService {
	private static int nextAccountNumber = 123456;
	@Autowired
	private SavingsRepository savingsService;
	@Autowired
	private SalaryRepository salaryService;
	@Autowired
	private UserRepository userService;
	@Autowired
	private TransactionService transactionService;

	@Override
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());

		savingsService.save(savingsAccount);

		return savingsService.findByAccountNumber(savingsAccount.getAccountNumber());
	}

	@Override
	public SalaryAccount createSalaryAccount() {
		SalaryAccount salaryAccount = new SalaryAccount();
		salaryAccount.setAccountBalance(new BigDecimal(0.0));
		salaryAccount.setAccountNumber(accountGen());
		salaryService.save(salaryAccount);
		return salaryService.findByAccountNumber(salaryAccount.getAccountNumber());
	}

	private int accountGen() {
		return ++nextAccountNumber;
	}

	@Override
	public void deposit(String accountType, double amount, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		System.out.println(user.getFirstName());
		if (accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
			int acnt = savingsAccount.getAccountNumber();
			System.out.println(acnt);
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsService.save(savingsAccount);

			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account",
					"Savings Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
			transactionService.saveSavingsDepositTransaction(savingsTransaction);
		} else if (accountType.equalsIgnoreCase("Salary")) {
			SalaryAccount salary = user.getSalaryAccount();
			System.out.println(salary.getAccountBalance());
			salary.setAccountBalance(salary.getAccountBalance().add(new BigDecimal(amount)));
			salaryService.save(salary);
			Date date = new Date();
			SalaryTransaction salaryt = new SalaryTransaction(date, "Deposit to Salary Account", "Salary Account",
					"Finished", amount, salary.getAccountBalance(), salary);
			transactionService.saveSalaryDepositTransaction(salaryt);
		}
	}

	@Override
	public void withdraw(String accountType, double amount, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		BigDecimal salaryAccountBalance = user.getSalaryAccount().getAccountBalance();
		BigDecimal savingsAccountBalance = user.getSavingsAccount().getAccountBalance();

		BigDecimal minumumSalaryBalance = new BigDecimal(1000);
		BigDecimal minumumSavingsBalance = new BigDecimal(5000);

		BigDecimal b = new BigDecimal(amount, MathContext.DECIMAL64);

		BigDecimal restrictedSalary = salaryAccountBalance.subtract(b);

		BigDecimal restrictedSavings = savingsAccountBalance.subtract(b);

		if (accountType.equalsIgnoreCase("Salary") && (salaryAccountBalance.compareTo(b)) == 1
				&& (restrictedSalary.compareTo(minumumSalaryBalance) == 1)) {
			SalaryAccount salary = user.getSalaryAccount();
			salary.setAccountBalance(salary.getAccountBalance().subtract(new BigDecimal(amount)));
			salaryService.save(salary);
			Date date = new Date();
			SalaryTransaction salaryt = new SalaryTransaction(date, "Withdraw from Salary Account", "Salary Account",
					"Finished", amount, salary.getAccountBalance(), salary);
			transactionService.saveSalaryDepositTransaction(salaryt);
		} else if (accountType.equalsIgnoreCase("Savings") && (savingsAccountBalance.compareTo(b)) == 1
				&& (restrictedSavings.compareTo(minumumSavingsBalance) == 1)) {
			SavingsAccount savings = user.getSavingsAccount();
			savings.setAccountBalance(savings.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsService.save(savings);
			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from savings Account",
					"Savings Account", "Finished", amount, savings.getAccountBalance(), savings);
			transactionService.saveSavingsDepositTransaction(savingsTransaction);
		} else {
			System.out.println("Oops,Can't Complete Transaction!!");
		}
	}

}
