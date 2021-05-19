package com.zensar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.model.SalaryAccount;

@Repository
public interface SalaryRepository extends CrudRepository<SalaryAccount, Integer> {

	SalaryAccount findByAccountNumber(int accountNumber);
}
