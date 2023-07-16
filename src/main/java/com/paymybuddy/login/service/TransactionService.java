package com.paymybuddy.login.service;

import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.repository.TransactionRepository;
import com.paymybuddy.login.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    // Dependency injection
    @Autowired
    TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Get all transactions history stored in the database
     */
    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionsByTransactionNumber(Long transaction_number) {
        return transactionRepository.findById(Math.toIntExact(transaction_number));
    }

    /**
     * Get all transactions of the current connected user
     */

    public Iterable<Transaction> getTransactionsOfConnectedUser(String email) {
        return transactionRepository.findByUserEmail(email);
    }


    /**
     * @param email Get history of the transactions for a certain user
     */
 /*
    public Optional<Transaction> getTransactionsByEmail(String email) {
        return transactionRepository.findById(toString(email));
    }

    /**
     *  Add a new transaction to the database
     */



    /*
    public Transaction addTransaction(Transaction transaction) {
        Transaction transaction1 = new Transaction();

      //  transaction1.setUserEmail(HttpSession.get);
        transaction1.setBuddyEmail(transaction.getBuddyEmail());
        transaction1.setDate(new Date());
        transaction1.setTransactionAmount(transaction1.getTransactionAmount());
        // Fee to add here
        transaction1.setDescription(transaction1.getDescription());

        return transaction1;

    }

     */
    @Transactional
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


    /**
     * Send money to your buddy
     */


    /**
     * Check enough money
     */

    public boolean checkSuficientBalance(String userAccountEmail, BigDecimal amount) {
        UserAccount userAccount = userAccountRepository.findByEmail(userAccountEmail).orElse(null);
        if (userAccount != null ) {
            BigDecimal currentAccountBalance = userAccount.getAccountBalance();

          if (currentAccountBalance.compareTo(amount) == -1) {
            return false;
            } else {
                return true;
            }


        }
        return false;
    }

    /*
    @Transactional
    public Transaction newTransaction(String from_email, String to_email, int transactionAmount, int transactionFee, String description) {
        Transaction transaction = new Transaction();

        //transaction.setUserEmail(from_email);
        //transaction.setBuddyEmail(to_email);
        transaction.setDate(new Date());
        transaction.setTransactionAmount(transactionAmount);
        transaction.setTransactionFee((long) (transactionAmount * FeeRateForEachTransaction));
        transaction.setDescription(description);

        //return null;
        return transaction;



    }
     */

}

