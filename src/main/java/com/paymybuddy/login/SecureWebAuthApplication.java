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
import java.math.BigDecimal;
import java.util.Date;

import static com.paymybuddy.login.constants.Constants.FeeRateForEachTransaction;


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
		System.out.println("*** Liste des utilisateurs ***");
		accounts.forEach(account -> System.out.println(account.getEmail()));

		System.out.println("*** Liste des descriptions ***");
		Iterable<Transaction> transactions = transactionService.getTransactions();
		transactions.forEach(transaction -> System.out.println(transaction.getDescription()));

		System.out.println("*** Liste des contacts ***");
		Iterable<BuddyContact> buddies = buddyContactService.getBuddyContacts();
		//buddies.forEach(buddyContact -> System.out.println(buddyContact.getBuddyContactId().getBuddyEmail()));



		System.out.println("*** Fin ***");
		/**
		 *
		 * @Param email
		 * @Param buddyContact
		 * Create a new transaction between two buddy contacts
		 */

		transactionService.getTransactions().forEach(
				transaction -> System.out.println(transaction.getDescription()));

		Transaction newTransaction = new Transaction();
		//newTransaction.setUserEmail("UserTest@mail.com");
		//newTransaction.set("BuddyTest@mail.com");
		newTransaction.setDate(new Date());
		newTransaction.setDescription("Paying my buddy Joe");
		//newTransaction.setTransactionAmount((10));
		newTransaction.setTransactionAmount(BigDecimal.valueOf((10)));
		//newTransaction.setTransactionFee((long) FeeRateForEachTransaction * newTransaction.getTransactionAmount());
		newTransaction.setTransactionFee(FeeRateForEachTransaction.multiply(newTransaction.getTransactionAmount()));
		//newTransaction = transactionService.addTransaction(newTransaction);

		transactionService.getTransactions().forEach(
				transaction -> System.out.println(transaction.getDescription()));
	}

}




