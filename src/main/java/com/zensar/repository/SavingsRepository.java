package com.zensar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.model.SavingsAccount;

@Repository
public interface SavingsRepository extends CrudRepository<SavingsAccount, Integer> {

	SavingsAccount findByAccountNumber(int accountNumber);
}
