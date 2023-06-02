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
	public void run(String... args) throws Exception {

		Iterable<UserAccount> accounts = userAccountService.getUserAccounts();
		accounts.forEach(account -> System.out.println(account.getEmail()));

		Iterable<Transaction> transactions = transactionService.getTransactions();
		transactions.forEach(transaction -> System.out.println(transaction.getDescription()));

		Iterable<BuddyContact> buddies = buddyContactService.getBuddyContacts();
		buddies.forEach(buddyContact -> System.out.println(buddyContact.getBuddyContactId()));
	}



}
