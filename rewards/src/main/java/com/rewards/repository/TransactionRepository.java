package com.rewards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rewards.domain.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

	List<Transaction> findByCustomerName(String customerName);
	
	List<Transaction> findAll();

}
