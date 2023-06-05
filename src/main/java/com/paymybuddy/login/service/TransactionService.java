package com.paymybuddy.login.service;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionsByTransactionNumber(Long transaction_number) {
        return transactionRepository.findById(Math.toIntExact(transaction_number));
    }
    /**
     * @param email
     * Get history of the transactions for a certain user
     */
    /*
    public Optional<Transaction> getTransactionsByEmail(String email) {
        return transactionRepository.findById(toString(email));
    }
*/
    /**
     *
     */
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

}
