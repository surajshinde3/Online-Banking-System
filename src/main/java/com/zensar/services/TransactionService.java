package com.zensar.services;

import java.util.List;

import com.zensar.model.SalaryTransaction;
import com.zensar.model.SavingsTransaction;

public interface TransactionService {

	void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);

	void saveSalaryDepositTransaction(SalaryTransaction salaryTransaction);

	void saveSalaryWithdrawTransaction(SalaryTransaction salaryTransaction);

	void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);

	List<SalaryTransaction> findSalaryTransactionList(String username);

	List<SavingsTransaction> findSavingsTransactionList(String username);
}
