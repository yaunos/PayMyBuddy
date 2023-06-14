package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Integer> {
    Iterable<Transaction> findByUserEmail(String email);
}
