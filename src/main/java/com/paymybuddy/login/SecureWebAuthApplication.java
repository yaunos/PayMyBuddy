package com.paymybuddy.login;

import com.paymybuddy.login.model.BuddyContact;
import com.paymybuddy.login.model.Transaction;
import com.paymybuddy.login.model.UserAccount;
import com.paymybuddy.login.service.BuddyContactService;
import com.paymybuddy.login.service.TransactionService;
import com.paymybuddy.login.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;


@SpringBootApplication

public class SecureWebAuthApplication implements CommandLineRunner {

	@Autowired
	private BuddyContactService buddyContactService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private TransactionService transactionService;


	public static void main(String[] args) {
		SpringApplication.run(SecureWebAuthApplication.class, args);
	}

	@Override
	/**
	 * @Transactional to be able to add Transactions
	 */
	@Transactional
	public void run(String... args) throws Exception {

		Iterable<UserAccount> accounts = userAccountService.getUserAccounts();
		accounts.forEach(account -> System.out.println(account.getEmail()));


		Iterable<Transaction> transactions = transactionService.getTransactions();
		transactions.forEach(transaction -> System.out.println(transaction.getDescription()));


		Iterable<BuddyContact> buddies = buddyContactService.getBuddyContacts();
		buddies.forEach(buddyContact -> System.out.println(buddyContact.getBuddyContactId().getBuddy_email()));

		/**
		 *
		 * @Param email
		 * @Param buddyContact
		 * Create a new transaction between two buddy contacts
		 */

		transactionService.getTransactions().forEach(
				transaction -> System.out.println(transaction.getDescription()));

		Transaction newTransaction = new Transaction();
		//Email 1 newTra.setUse
		//Email 2
		newTransaction.setDescription("Paying my buddy Joe");

		newTransaction = transactionService.addTransaction(newTransaction);

		transactionService.getTransactions().forEach(
				transaction -> System.out.println(transaction.getDescription()));
	}

}




