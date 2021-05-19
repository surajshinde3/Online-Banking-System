package com.zensar.services;

import java.security.Principal;
import java.util.List;

import com.zensar.model.SalaryAccount;
import com.zensar.model.SavingsAccount;

public interface AccountService {

	SavingsAccount createSavingsAccount();

	SalaryAccount createSalaryAccount();

	void deposit(String accountType, double amount, Principal principal);

	void withdraw(String accountType, double amount, Principal principal);

}
