package com.zensar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.model.SalaryTransaction;

@Repository
public interface SalaryTransactionRepository extends CrudRepository<SalaryTransaction, Integer> {

}
