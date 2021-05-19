package com.zensar.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SalaryAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int accountNumber;
	private BigDecimal accountBalance;

	@OneToMany(mappedBy = "salaryAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

	private List<SalaryTransaction> salaryTransactionList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<SalaryTransaction> getSalaryTransactionList() {
		return salaryTransactionList;
	}

	public void setSalaryTransactionList(List<SalaryTransaction> salaryTransactionList) {
		this.salaryTransactionList = salaryTransactionList;
	}

}
