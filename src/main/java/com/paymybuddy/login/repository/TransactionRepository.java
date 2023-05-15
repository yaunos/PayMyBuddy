package com.paymybuddy.login.repository;

import com.paymybuddy.login.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository <Transaction, Integer> {
}
