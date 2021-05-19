package com.zensar.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.model.SalaryTransaction;
import com.zensar.model.SavingsTransaction;
import com.zensar.model.User;
import com.zensar.repository.SalaryTransactionRepository;
import com.zensar.repository.SavingsTransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private SavingsTransactionRepository trepo;
	@Autowired
	private SalaryTransactionRepository srepo;
	@Autowired
	private UserService service;

	@Override
	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
		trepo.save(savingsTransaction);

	}

	@Override
	public void saveSalaryDepositTransaction(SalaryTransaction salaryTransaction) {
		srepo.save(salaryTransaction);
	}

	@Override
	public void saveSalaryWithdrawTransaction(SalaryTransaction salaryTransaction) {
		srepo.save(salaryTransaction);

	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		trepo.save(savingsTransaction);

	}

	public List<SalaryTransaction> findSalaryTransactionList(String username) {
		User user = service.findByUsername(username);
		List<SalaryTransaction> salaryTransactionList = user.getSalaryAccount().getSalaryTransactionList();

		Collections.sort(salaryTransactionList, new Comparator<SalaryTransaction>() {
			@Override
			public int compare(SalaryTransaction o1, SalaryTransaction o2) {

				return o2.getDate().compareTo(o1.getDate());
			}
		});
		return salaryTransactionList;
	}

	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = service.findByUsername(username);
		List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Collections.sort(savingsTransactionList, new Comparator<SavingsTransaction>() {
			@Override
			public int compare(SavingsTransaction o1, SavingsTransaction o2) {

				/*
				 * String o1F = formatter.format(o1.getDate()); String o2F =
				 * formatter.format(o2.getDate());
				 */
				return o2.getDate().compareTo(o1.getDate());
			}
		});
		return savingsTransactionList;
	}

}
