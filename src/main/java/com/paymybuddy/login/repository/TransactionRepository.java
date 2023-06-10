package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository <Transaction, Integer> {
    Optional<Transaction> findByUserEmail(String from_email);
}
