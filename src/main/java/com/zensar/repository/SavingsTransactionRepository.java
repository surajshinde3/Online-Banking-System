package com.zensar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zensar.model.SavingsTransaction;

@Repository
public interface SavingsTransactionRepository extends CrudRepository<SavingsTransaction, Integer> {

}
